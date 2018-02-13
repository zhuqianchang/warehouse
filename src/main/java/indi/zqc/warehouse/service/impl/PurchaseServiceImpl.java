package indi.zqc.warehouse.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import indi.zqc.warehouse.constant.Constants;
import indi.zqc.warehouse.dao.*;
import indi.zqc.warehouse.enums.OrderStatus;
import indi.zqc.warehouse.enums.PurchaseStatus;
import indi.zqc.warehouse.enums.PurchaseType;
import indi.zqc.warehouse.exception.BusinessException;
import indi.zqc.warehouse.model.Order;
import indi.zqc.warehouse.model.Purchase;
import indi.zqc.warehouse.model.PurchaseMaterial;
import indi.zqc.warehouse.model.PurchaseOrder;
import indi.zqc.warehouse.model.condition.PurchaseCondition;
import indi.zqc.warehouse.service.PurchaseMaterialService;
import indi.zqc.warehouse.service.PurchaseService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Title : PurchaseServiceImpl.java
 * Package : indi.zqc.warehouse.service.impl
 * Description : 采购清单服务实现
 * Create on : 2018/2/1 17:46
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
@Transactional
@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseDao purchaseDao;

    @Autowired
    private PurchaseMaterialDao purchaseMaterialDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderProductionDao orderProductionDao;

    @Autowired
    private PurchaseOrderDao purchaseOrderDao;

    @Autowired
    private PurchaseMaterialService purchaseMaterialService;

    @Override
    public int insertPurchase(Purchase purchase) {
        return purchaseDao.insertPurchase(purchase);
    }

    @Override
    public int deletePurchase(String purchaseCode) {
        Purchase purchase = selectPurchase(purchaseCode);
        if (purchase == null) {
            throw new BusinessException("采购清单不存在");
        }
        if (StringUtils.equals(purchase.getPurchaseStatus(), PurchaseStatus.FINISHED.getKey())) {
            throw new BusinessException("采购清单已完成，不能删除");
        }
        //删除采购清单与物料的关系
        purchaseMaterialDao.deletePurchaseMaterial(purchaseCode);
        //删除采购清单时，修改订单的状态为创建
        List<PurchaseOrder> purchaseOrders = purchaseOrderDao.selectPurchaseOrder(purchaseCode);
        for (PurchaseOrder purchaseOrder : purchaseOrders) {
            Order order = orderDao.selectOrder(purchaseOrder.getOrderCode());
            if (!StringUtils.equals(order.getOrderStatus(), OrderStatus.CREATED.getKey())) {
                order.setOrderStatus(OrderStatus.CREATED.getKey());
                orderDao.updateOrder(order);
            }
        }
        //删除采购清单与订单的关系
        purchaseOrderDao.deletePurchaseOrder(purchaseCode);
        //删除采购清单
        return purchaseDao.deletePurchase(purchaseCode);
    }

    @Override
    public int updatePurchase(Purchase purchase) {
        return purchaseDao.updatePurchase(purchase);
    }

    @Override
    public Purchase selectPurchase(String purchaseCode) {
        return purchaseDao.selectPurchase(purchaseCode);
    }

    @Override
    public Page<Purchase> selectPurchasePage(PurchaseCondition condition) {
        PageHelper.startPage(condition.getPageNum(), condition.getNumPerPage());
        return purchaseDao.selectPurchasePage(condition);
    }

    @Override
    public int finishPurchase(String purchaseCode, String userCode) {
        Purchase purchase = selectPurchase(purchaseCode);
        if (purchase == null) {
            throw new BusinessException("采购清单不存在");
        }
        if (StringUtils.equals(purchase.getPurchaseStatus(), PurchaseStatus.FINISHED.getKey())) {
            throw new BusinessException("采购清单已完成");
        }
        purchase.setPurchaseStatus(PurchaseStatus.FINISHED.getKey());
        purchase.setModifyUser(userCode);
        purchase.setModifyDateTime(new Date());
        //完成采购清单
        int num = updatePurchase(purchase);
        //完成采购清单的同时完成关联的订单
        List<PurchaseOrder> purchaseOrders = purchaseOrderDao.selectPurchaseOrder(purchaseCode);
        for (PurchaseOrder purchaseOrder : purchaseOrders) {
            Order order = orderDao.selectOrder(purchaseOrder.getOrderCode());
            if (order != null) {
                order.setOrderStatus(OrderStatus.FINISHED.getKey());
                order.setModifyUser(userCode);
                order.setModifyDateTime(new Date());
                orderDao.updateOrder(order);
            }
        }
        return num;
    }

    @Override
    public int savePurchase(Purchase purchase, String materials) {
        Purchase oldPurchase = selectPurchase(purchase.getPurchaseCode());
        int num;
        if (oldPurchase == null) {
            purchase.setPurchaseType(PurchaseType.MANUAL.getKey());
            purchase.setPurchaseStatus(PurchaseStatus.CREATED.getKey());
            num = insertPurchase(purchase);
        } else {
            oldPurchase.setModifyUser(purchase.getModifyUser());
            oldPurchase.setModifyDateTime(purchase.getModifyDateTime());
            num = updatePurchase(oldPurchase);
        }
        purchaseMaterialDao.deletePurchaseMaterial(purchase.getPurchaseCode());
        List<PurchaseMaterial> purchaseMaterials = getPurchaseMaterialList(materials, purchase.getPurchaseCode());
        for (PurchaseMaterial purchaseMaterial : purchaseMaterials) {
            purchaseMaterialDao.insertPurchaseMaterial(purchaseMaterial);
        }
        return num;
    }

    /**
     * 采购物料数据
     */
    private List<PurchaseMaterial> getPurchaseMaterialList(String materials, String purchaseCode) {
        List<PurchaseMaterial> purchaseMaterials = new ArrayList<>();
        JSONArray jsonArray = JSONArray.parseArray(materials);
        for (int i = 0; jsonArray != null && i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            PurchaseMaterial purchaseMaterial = new PurchaseMaterial();
            purchaseMaterial.setPurchaseCode(purchaseCode);
            purchaseMaterial.setMaterialCode(jsonObject.getString("materialCode"));
            purchaseMaterial.setQuantity(jsonObject.getInteger("quantity"));
            purchaseMaterials.add(purchaseMaterial);
        }
        return purchaseMaterials;
    }

    @Override
    public String producePurchase(String orderCodes, String userCode) {
        //采购清单编号
        String purchaseCode = newPurchaseCode();
        Purchase purchase = new Purchase();
        purchase.setPurchaseCode(purchaseCode);
        purchase.setPurchaseType(PurchaseType.AUTO.getKey());
        purchase.setPurchaseStatus(PurchaseStatus.CREATED.getKey());
        purchase.setCreateUser(userCode);
        purchase.setCreateDateTime(new Date());
        purchase.setModifyUser(userCode);
        purchase.setModifyDateTime(new Date());
        purchaseDao.insertPurchase(purchase);
        for (String orderCode : orderCodes.split(",")) {
            Order order = orderDao.selectOrder(orderCode);
            if (order == null) {
                throw new BusinessException(String.format("订单[%s]不存在", orderCode));
            }
            if (StringUtils.equals(order.getOrderStatus(), OrderStatus.PRODUCED.getKey()) ||
                    StringUtils.equals(order.getOrderStatus(), OrderStatus.FINISHED.getKey())) {
                throw new BusinessException(String.format("订单[%s]已生成或已完成", orderCode));
            }
            //修改订单状态
            order.setOrderStatus(OrderStatus.PRODUCED.getKey());
            order.setModifyUser(userCode);
            order.setModifyDateTime(new Date());
            orderDao.updateOrder(order);
            //保存采购订单
            PurchaseOrder purchaseOrder = new PurchaseOrder();
            purchaseOrder.setPurchaseCode(purchaseCode);
            purchaseOrder.setOrderCode(orderCode);
            purchaseOrderDao.insertPurchaseOrder(purchaseOrder);
        }
        return "采购清单已生成，清单编号:" + purchaseCode;
    }

    /**
     * 采购清单编号
     */
    private String newPurchaseCode() {
        return Constants.QD_PREFIX + DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
    }

    @Override
    public void exportPurchase(String purchaseCode, HttpServletResponse response) {
        Purchase purchase = selectPurchase(purchaseCode);
        List<PurchaseMaterial> purchaseMaterials = purchaseMaterialService.selectPurchaseMaterial(purchaseCode);
        if (purchase == null) {
            throw new BusinessException("采购清单不存在");
        }
        OutputStream os = null;
        InputStream is = null;
        XSSFWorkbook workbook;
        XSSFSheet sheet;
        try {
            response.setContentType("application/vnd.ms-excel");
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(("采购清单" + purchaseCode).getBytes(), "ISO8859-1") + ".xlsx");
            os = response.getOutputStream();
            is = new ClassPathResource(Constants.TEMPLATE_PURCHASE).getInputStream();
            workbook = new XSSFWorkbook(is);
            sheet = workbook.getSheetAt(0);
            XSSFRow row = sheet.getRow(1);
            //采购编号
            row.createCell(1).setCellValue(purchaseCode);
            //日期
            row.getCell(3).setCellValue(row.getCell(3).getStringCellValue() + DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
            //物料信息
            XSSFRow styleRow = sheet.getRow(2);
            for (int i = 0; i < purchaseMaterials.size(); i++) {
                PurchaseMaterial purchaseMaterial = purchaseMaterials.get(i);
                row = sheet.createRow(i + 3);
                XSSFCell cell = row.createCell(0);
                cell.setCellStyle(styleRow.getCell(0).getCellStyle());
                cell.setCellValue(i + 1);
                cell = row.createCell(1);
                cell.setCellStyle(styleRow.getCell(1).getCellStyle());
                cell.setCellValue(purchaseMaterial.getMaterialCode());
                cell = row.createCell(2);
                cell.setCellStyle(styleRow.getCell(2).getCellStyle());
                cell.setCellValue(purchaseMaterial.getMaterialText());
                cell = row.createCell(3);
                cell.setCellStyle(styleRow.getCell(3).getCellStyle());
                cell.setCellValue(purchaseMaterial.getQuantity());
            }
            workbook.write(os);
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }
    }
}

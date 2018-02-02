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
import indi.zqc.warehouse.model.*;
import indi.zqc.warehouse.model.condition.PurchaseCondition;
import indi.zqc.warehouse.service.PurchaseService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private PurchaseProductionDao purchaseProductionDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderProductionDao orderProductionDao;

    @Override
    public int insertPurchase(Purchase purchase) {
        return purchaseDao.insertPurchase(purchase);
    }

    @Override
    public int deletePurchase(String purchaseCode) {
        int num = purchaseDao.deletePurchase(purchaseCode);
        purchaseMaterialDao.deletePurchaseMaterial(purchaseCode);
        purchaseProductionDao.deletePurchaseProduction(purchaseCode);
        return num;
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
    public int savePurchase(Purchase purchase, String materials) {
        Purchase oldPurchase = selectPurchase(purchase.getPurchaseCode());
        int num;
        if (oldPurchase == null) {
            num = insertPurchase(purchase);
        } else {
            num = updatePurchase(purchase);
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
    public String producePurchase(String orderCode, String userCode) {
        Order order = orderDao.selectOrder(orderCode);
        if (order == null) {
            throw new BusinessException("订单不存在");
        }
        //修改订单状态
        order.setOrderStatus(OrderStatus.FINISHED.getKey());
        order.setModifyUser(userCode);
        order.setModifyDateTime(new Date());
        orderDao.updateOrder(order);
        String purchaseCode = newPurchaseCode();
        //保存采购清单
        Purchase purchase = new Purchase();
        purchase.setPurchaseCode(purchaseCode);
        purchase.setPurchaseType(PurchaseType.AUTO.getKey());
        purchase.setPurchaseStatus(PurchaseStatus.CREATED.getKey());
        purchase.setCreateUser(userCode);
        purchase.setCreateDateTime(new Date());
        purchase.setModifyUser(userCode);
        purchase.setModifyDateTime(new Date());
        purchaseDao.insertPurchase(purchase);
        //将订单中的成品转换成清单中的成品
        List<OrderProduction> orderProductions = orderProductionDao.selectOrderProduction(orderCode);
        for (OrderProduction orderProduction : orderProductions) {
            PurchaseProduction purchaseProduction = new PurchaseProduction();
            purchaseProduction.setPurchaseCode(purchaseCode);
            purchaseProduction.setProductionCode(orderProduction.getProductionCode());
            purchaseProduction.setQuantity(orderProduction.getQuantity());
            purchaseProductionDao.insertPurchaseProduction(purchaseProduction);
        }
        return "采购清单已生成，清单编号:" + purchaseCode;
    }

    /**
     * 采购清单编号
     */
    private String newPurchaseCode() {
        return Constants.QD_PREFIX + DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
    }
}
package indi.zqc.warehouse.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import indi.zqc.warehouse.constant.Constants;
import indi.zqc.warehouse.dao.OperationStockDao;
import indi.zqc.warehouse.dao.StockDao;
import indi.zqc.warehouse.enums.OperationType;
import indi.zqc.warehouse.exception.BusinessException;
import indi.zqc.warehouse.model.OperationStock;
import indi.zqc.warehouse.model.Stock;
import indi.zqc.warehouse.model.condition.OperationStockCondition;
import indi.zqc.warehouse.service.OperationStockService;
import indi.zqc.warehouse.util.ExcelUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
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
 * Title : OperationStockServiceImpl.java
 * Package : indi.zqc.warehouse.service.impl
 * Description : 出入库服务实现
 * Create on : 2018/1/29 20:56
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
@Transactional
@Service
public class OperationStockServiceImpl implements OperationStockService {

    @Autowired
    private OperationStockDao operationStockDao;

    @Autowired
    private StockDao stockDao;

    @Override
    public Page<OperationStock> selectOperationStockPage(OperationStockCondition condition) {
        PageHelper.startPage(condition.getPageNum(), condition.getNumPerPage());
        return operationStockDao.selectOperationStockPage(condition);
    }

    @Override
    public String inputStock(String stocks, String userCode) {
        String receiptCode = newReceiptCode();
        List<OperationStock> operationStocks = getOperationStockList(stocks, receiptCode, userCode, OperationType.INPUT);
        //入库
        inputStock(operationStocks);
        return receiptCode;
    }

    @Override
    public String outputStock(String stocks, String userCode) {
        String receiptCode = newReceiptCode();
        List<OperationStock> operationStocks = getOperationStockList(stocks, receiptCode, userCode, OperationType.OUTPUT);
        //出库
        outputStock(operationStocks);
        return receiptCode;
    }

    /**
     * 入库处理
     */
    public void inputStock(List<OperationStock> operationStockList) {
        for (OperationStock operationStock : operationStockList) {
            inputStock(operationStock);
        }
    }

    /**
     * 入库处理
     */
    public void inputStock(OperationStock operationStock) {
        Stock stock = stockDao.selectStock(operationStock.getWarehouseCode(), operationStock.getMaterialCode());
        if (StringUtils.equals(operationStock.getOperationType(), OperationType.INPUT.getKey())) {
            //入库
            if (stock == null) {
                throw new BusinessException(String.format("仓库[%s]与物料[%s]的关系不存在，请先维护", operationStock.getWarehouseCode(), operationStock.getMaterialCode()));
            } else {
                stock.setStock(stock.getStock() + operationStock.getQuantity());
                stock.setModifyUser(operationStock.getModifyUser());
                stock.setModifyDateTime(operationStock.getModifyDateTime());
                stockDao.updateStock(stock);
            }
            operationStockDao.insertOperationStock(operationStock);
        } else {
            throw new BusinessException("操作类型异常，请重试");
        }
    }

    /**
     * 出库处理
     */
    public void outputStock(List<OperationStock> operationStockList) {
        for (OperationStock operationStock : operationStockList) {
            outputStock(operationStock);
        }
    }

    /**
     * 出库处理
     */
    public void outputStock(OperationStock operationStock) {
        Stock stock = stockDao.selectStock(operationStock.getWarehouseCode(), operationStock.getMaterialCode());
        if (StringUtils.equals(operationStock.getOperationType(), OperationType.OUTPUT.getKey())) {
            //出库
            if (stock == null) {
                throw new BusinessException(String.format("仓库[%s]与物料[%s]的关系不存在，请先维护", operationStock.getWarehouseCode(), operationStock.getMaterialCode()));
            } else if (stock.getStock() < operationStock.getQuantity()) {
                throw new BusinessException(String.format("仓库[%s]中的物料[%s]库存不足", operationStock.getWarehouseCode(), operationStock.getMaterialCode()));
            } else {
                stock.setStock(stock.getStock() - operationStock.getQuantity());
                stock.setModifyUser(operationStock.getModifyUser());
                stock.setModifyDateTime(operationStock.getModifyDateTime());
                stockDao.updateStock(stock);
                operationStockDao.insertOperationStock(operationStock);
            }
        } else {
            throw new BusinessException("操作类型异常，请重试");
        }
    }

    /**
     * 出入库数据
     */
    private List<OperationStock> getOperationStockList(String stocks, String receiptCode, String userCode, OperationType operationType) {
        List<OperationStock> operationStocks = new ArrayList<>();
        JSONArray jsonArray = JSONArray.parseArray(stocks);
        for (int i = 0; jsonArray != null && i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            OperationStock operationStock = new OperationStock();
            operationStock.setReceiptCode(receiptCode);
            operationStock.setWarehouseCode(jsonObject.getString("warehouseCode"));
            operationStock.setWarehouseText(jsonObject.getString("warehouseText"));
            operationStock.setMaterialCode(jsonObject.getString("materialCode"));
            operationStock.setMaterialText(jsonObject.getString("materialText"));
            operationStock.setQuantity(jsonObject.getInteger("quantity"));
            operationStock.setOperationType(operationType.getKey());
            operationStock.setCreateUser(userCode);
            operationStock.setCreateDateTime(new Date());
            operationStock.setModifyUser(userCode);
            operationStock.setModifyDateTime(new Date());
            operationStocks.add(operationStock);
        }
        return operationStocks;
    }

    /**
     * 单据编号
     */
    private String newReceiptCode() {
        return Constants.DJ_PREFIX + DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
    }

    @Override
    public void exportRecord(OperationStockCondition condition, HttpServletResponse response) {
        Page<OperationStock> operationStocks = operationStockDao.selectOperationStockPage(condition);
        OutputStream os = null;
        InputStream is = null;
        XSSFWorkbook workbook;
        XSSFSheet sheet;
        try {
            response.setContentType("application/vnd.ms-excel");
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(("出入库清单").getBytes(), "ISO8859-1") + ".xlsx");
            os = response.getOutputStream();
            is = new ClassPathResource(Constants.TEMPLATE_OPERATION_STOCK).getInputStream();
            workbook = new XSSFWorkbook(is);
            sheet = workbook.getSheetAt(0);
            XSSFRow styleRow = sheet.getRow(1);
            //出入库信息
            for (int i = 0; i < operationStocks.size(); i++) {
                OperationStock operationStock = operationStocks.get(i);
                XSSFRow row = sheet.createRow(i + 2);
                ExcelUtils.setCell(row, 0, styleRow.getCell(0).getCellStyle(), i + 1);
                ExcelUtils.setCell(row, 1, styleRow.getCell(1).getCellStyle(), operationStock.getReceiptCode());
                ExcelUtils.setCell(row, 2, styleRow.getCell(2).getCellStyle(), operationStock.getWarehouseCode());
                ExcelUtils.setCell(row, 3, styleRow.getCell(3).getCellStyle(), operationStock.getWarehouseText());
                ExcelUtils.setCell(row, 4, styleRow.getCell(4).getCellStyle(), operationStock.getMaterialCode());
                ExcelUtils.setCell(row, 5, styleRow.getCell(5).getCellStyle(), operationStock.getMaterialText());
                ExcelUtils.setCell(row, 6, styleRow.getCell(6).getCellStyle(), operationStock.getQuantity());
                ExcelUtils.setCell(row, 7, styleRow.getCell(7).getCellStyle(), operationStock.getOperationTypeText());
                ExcelUtils.setCell(row, 8, styleRow.getCell(8).getCellStyle(), operationStock.getCreateUserText());
                ExcelUtils.setCell(row, 9, styleRow.getCell(9).getCellStyle(), operationStock.getCreateDateTime());
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

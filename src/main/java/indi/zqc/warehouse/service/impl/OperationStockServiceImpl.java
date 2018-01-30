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
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
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
                stock = new Stock();
                stock.setWarehouseCode(operationStock.getWarehouseCode());
                stock.setMaterialCode(operationStock.getMaterialCode());
                stock.setStock(operationStock.getQuantity());
                stock.setCreateUser(operationStock.getCreateUser());
                stock.setCreateDateTime(operationStock.getCreateDateTime());
                stock.setModifyUser(operationStock.getModifyUser());
                stock.setModifyDateTime(operationStock.getModifyDateTime());
                stockDao.insertStock(stock);
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
    public void outputStock(List<OperationStock> operationStockList){
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
            if (stock == null || stock.getStock() < operationStock.getQuantity()) {
                throw new BusinessException(operationStock.getWarehouseText() + "中" + operationStock.getMaterialText() + "库存不足");
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
        for (int i = 0; i < jsonArray.size(); i++) {
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
        StringBuilder receiptCode = new StringBuilder(Constants.DJ_PREFIX);
        Calendar cal = Calendar.getInstance();
        receiptCode.append(cal.get(Calendar.YEAR));
        receiptCode.append(cal.get(Calendar.MINUTE) + 1);
        receiptCode.append(cal.get(Calendar.DAY_OF_MONTH));
        receiptCode.append(cal.get(Calendar.HOUR_OF_DAY));
        receiptCode.append(cal.get(Calendar.MINUTE));
        receiptCode.append(cal.get(Calendar.SECOND));
        return receiptCode.toString();
    }

}
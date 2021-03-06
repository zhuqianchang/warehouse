package indi.zqc.warehouse.model.condition;

import org.apache.commons.lang3.StringUtils;

/**
 * Title : OperationStockCondition.java
 * Package : indi.zqc.warehouse.model.condition
 * Description : 出入库查询条件
 * Create on : 2018/1/29 20:32
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class OperationStockCondition extends Condition{

    //单据编号
    private String receiptCode;

    //仓库编号
    private String warehouseCode;

    //仓库描述
    private String warehouseText;

    //物料编号
    private String materialCode;

    //物料描述
    private String materialText;

    //出入库类型
    private String operationType;

    //创建开始时间
    private String createDateStart;

    //创建结束时间
    private String createDateEnd;

    //炉号
    private String stoveCode;

    //批号
    private String batchCode;

    //生产编号
    private String productCode;

    //经办人
    private String operator;

    public String getReceiptCode() {
        return StringUtils.upperCase(receiptCode);
    }

    public void setReceiptCode(String receiptCode) {
        this.receiptCode = StringUtils.upperCase(receiptCode);
    }

    public String getWarehouseCode() {
        return StringUtils.upperCase(warehouseCode);
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = StringUtils.upperCase(warehouseCode);
    }

    public String getWarehouseText() {
        return warehouseText;
    }

    public void setWarehouseText(String warehouseText) {
        this.warehouseText = warehouseText;
    }

    public String getMaterialCode() {
        return StringUtils.upperCase(materialCode);
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = StringUtils.upperCase(materialCode);
    }

    public String getMaterialText() {
        return materialText;
    }

    public void setMaterialText(String materialText) {
        this.materialText = materialText;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getCreateDateStart() {
        return createDateStart;
    }

    public void setCreateDateStart(String createDateStart) {
        this.createDateStart = createDateStart;
    }

    public String getCreateDateEnd() {
        return createDateEnd;
    }

    public void setCreateDateEnd(String createDateEnd) {
        this.createDateEnd = createDateEnd;
    }

    public String getStoveCode() {
        return stoveCode;
    }

    public void setStoveCode(String stoveCode) {
        this.stoveCode = stoveCode;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}

package indi.zqc.warehouse.model.condition;

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

    public String getReceiptCode() {
        return receiptCode;
    }

    public void setReceiptCode(String receiptCode) {
        this.receiptCode = receiptCode;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getWarehouseText() {
        return warehouseText;
    }

    public void setWarehouseText(String warehouseText) {
        this.warehouseText = warehouseText;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
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
}

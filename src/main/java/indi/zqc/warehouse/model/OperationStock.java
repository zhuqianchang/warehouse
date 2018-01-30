package indi.zqc.warehouse.model;

import indi.zqc.warehouse.enums.OperationType;
import org.apache.commons.lang3.StringUtils;

/**
 * Title : OperationStock.java
 * Package : indi.zqc.warehouse.model
 * Description : 出入库记录
 * Create on : 2018/1/27 23:37
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class OperationStock extends Common{

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

    //出入库类型描述
    private String operationTypeText;

    //数量
    private Integer quantity;

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

    public String getOperationTypeText() {
        if (StringUtils.isBlank(operationTypeText) && StringUtils.isNotBlank(operationType)) {
            operationTypeText = OperationType.getValue(operationType);
        }
        return operationTypeText;
    }

    public void setOperationTypeText(String operationTypeText) {
        this.operationTypeText = operationTypeText;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

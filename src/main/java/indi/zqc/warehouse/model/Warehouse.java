package indi.zqc.warehouse.model;

/**
 * Title : Warehouse.java
 * Package : indi.zqc.warehouse.model
 * Description : 仓库
 * Create on : 2018/1/23 14:54
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class Warehouse extends Common {

    //仓库编号
    private String warehouseCode;

    //仓库名称
    private String warehouseText;

    //仓库类型
    private String warehouseType;

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

    public String getWarehouseType() {
        return warehouseType;
    }

    public void setWarehouseType(String warehouseType) {
        this.warehouseType = warehouseType;
    }
}

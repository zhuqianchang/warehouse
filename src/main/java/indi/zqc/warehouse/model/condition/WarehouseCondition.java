package indi.zqc.warehouse.model.condition;

/**
 * Title : WarehouseCondition.java
 * Package : indi.zqc.warehouse.model.condition
 * Description : 仓库查询条件
 * Create on : 2018/1/28 13:54
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class WarehouseCondition extends Condition {

    //仓库编号
    private String warehouseCode;

    //仓库描述
    private String warehouseText;

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
}

package indi.zqc.warehouse.model;

/**
 * Title : Inventory.java
 * Package : indi.zqc.warehouse.model
 * Description : 库存
 * Create on : 2018/1/23 15:26
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public abstract class Inventory extends Common{

    //仓库编号
    private String warehouseCode;

    //数量
    private Integer quantity;

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

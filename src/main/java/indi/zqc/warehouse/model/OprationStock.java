package indi.zqc.warehouse.model;

/**
 * Title : OprationStock.java
 * Package : indi.zqc.warehouse.model
 * Description : 出入库记录
 * Create on : 2018/1/27 23:37
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class OprationStock {

    //单据编号
    private String receiptCode;

    //物料编号
    private String material;

    //出入库类型
    private String type;

    //仓库编号
    private String warehouseCode;

    //数量
    private Integer quantity;

    public String getReceiptCode() {
        return receiptCode;
    }

    public void setReceiptCode(String receiptCode) {
        this.receiptCode = receiptCode;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

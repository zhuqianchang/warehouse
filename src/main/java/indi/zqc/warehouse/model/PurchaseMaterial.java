package indi.zqc.warehouse.model;

/**
 * Title : PurchaseMaterial.java
 * Package : indi.zqc.warehouse.model
 * Description : 采购物料
 * Create on : 2018/1/27 23:50
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class PurchaseMaterial extends Common{

    //采购编号
    private String purchaseCode;

    //物料编号
    private String materialCode;

    //数量
    private Integer quantity;

    public String getPurchaseCode() {
        return purchaseCode;
    }

    public void setPurchaseCode(String purchaseCode) {
        this.purchaseCode = purchaseCode;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

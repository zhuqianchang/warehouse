package indi.zqc.warehouse.model;

/**
 * Title : PurchaseProduction.java
 * Package : indi.zqc.warehouse.model
 * Description : 采购成品
 * Create on : 2018/2/2 17:58
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class PurchaseProduction {

    //订单编号
    private String purchaseCode;

    //成品编号
    private String productionCode;

    //成品描述
    private String productionText;

    //数量
    private Integer quantity;

    public String getPurchaseCode() {
        return purchaseCode;
    }

    public void setPurchaseCode(String purchaseCode) {
        this.purchaseCode = purchaseCode;
    }

    public String getProductionCode() {
        return productionCode;
    }

    public void setProductionCode(String productionCode) {
        this.productionCode = productionCode;
    }

    public String getProductionText() {
        return productionText;
    }

    public void setProductionText(String productionText) {
        this.productionText = productionText;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

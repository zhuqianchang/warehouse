package indi.zqc.warehouse.model;

/**
 * Title : PurchaseOrder.java
 * Package : indi.zqc.warehouse.model
 * Description : 采购订单
 * Create on : 2018/2/3 19:36
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class PurchaseOrder {

    //采购编号
    private String purchaseCode;

    //订单编号
    private String orderCode;

    //订单描述
    private String orderText;

    public String getPurchaseCode() {
        return purchaseCode;
    }

    public void setPurchaseCode(String purchaseCode) {
        this.purchaseCode = purchaseCode;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderText() {
        return orderText;
    }

    public void setOrderText(String orderText) {
        this.orderText = orderText;
    }
}

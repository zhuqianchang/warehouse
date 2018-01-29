package indi.zqc.warehouse.model;

/**
 * Title : PurchaseOrder.java
 * Package : indi.zqc.warehouse.model
 * Description : 采购订单物料
 * Create on : 2018/1/27 23:47
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class PurchaseOrder extends Common {

    //采购编号
    private String purchaseCode;

    //订单编号
    private String orderCode;

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
}

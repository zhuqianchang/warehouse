package indi.zqc.warehouse.model;

/**
 * Title : OrderProduction.java
 * Package : indi.zqc.warehouse.model
 * Description : 订单成品
 * Create on : 2018/2/2 15:09
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class OrderProduction {

    //订单编号
    private String orderCode;

    //成品编号
    private String productionCode;

    //成品描述
    private String productionText;

    //数量
    private Integer quantity;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
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

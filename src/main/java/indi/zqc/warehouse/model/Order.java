package indi.zqc.warehouse.model;

import java.util.Date;

/**
 * Title : Order.java
 * Package : indi.zqc.warehouse.model
 * Description : 订单
 * Create on : 2018/1/27 23:41
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class Order {

    //订单编号
    private String orderCode;

    //成品编号
    private String productionCode;

    //数量
    private Integer quantity;

    //生产日期
    private Date productTime;

    //状态
    private String status;

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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getProductTime() {
        return productTime;
    }

    public void setProductTime(Date productTime) {
        this.productTime = productTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

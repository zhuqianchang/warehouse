package indi.zqc.warehouse.model.condition;

import org.apache.commons.lang3.StringUtils;

/**
 * Title : OrderCondition.java
 * Package : indi.zqc.warehouse.model.condition
 * Description : 订单查询条件
 * Create on : 2018/2/2 15:20
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class OrderCondition extends Condition {

    //订单编号
    private String orderCode;

    //订单描述
    private String orderText;

    //生产日期开始
    private String productDateStart;

    //生产日期结束
    private String productDateEnd;

    //订单状态
    private String orderStatus;

    public String getOrderCode() {
        return StringUtils.upperCase(orderCode);
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = StringUtils.upperCase(orderCode);
    }

    public String getOrderText() {
        return orderText;
    }

    public void setOrderText(String orderText) {
        this.orderText = orderText;
    }

    public String getProductDateStart() {
        return productDateStart;
    }

    public void setProductDateStart(String productDateStart) {
        this.productDateStart = productDateStart;
    }

    public String getProductDateEnd() {
        return productDateEnd;
    }

    public void setProductDateEnd(String productDateEnd) {
        this.productDateEnd = productDateEnd;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}

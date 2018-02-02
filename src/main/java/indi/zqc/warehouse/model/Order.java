package indi.zqc.warehouse.model;

import indi.zqc.warehouse.enums.OrderStatus;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
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
public class Order extends Common {

    //订单编号
    private String orderCode;

    //订单描述
    private String orderText;

    //生产日期
    private String productDateStr;

    //生产日期
    private Date productDate;

    //订单状态
    private String orderStatus;

    //订单状态
    private String orderStatusText;

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

    public String getProductDateStr() {
        if (StringUtils.isBlank(productDateStr) && productDate != null) {
            productDateStr = DateFormatUtils.format(productDate, "yyyy-MM-dd");
        }
        return productDateStr;
    }

    public void setProductDateStr(String productDateStr) {
        this.productDateStr = productDateStr;
    }

    public Date getProductDate() throws ParseException {
        if (productDate == null && StringUtils.isNotBlank(productDateStr)) {
            productDate = DateUtils.parseDate(productDateStr, "yyyy-MM-dd");
        }
        return productDate;
    }

    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatusText() {
        if (StringUtils.isBlank(orderStatusText)) {
            orderStatusText = OrderStatus.getValue(orderStatus);
        }
        return orderStatusText;
    }

    public void setOrderStatusText(String orderStatusText) {
        this.orderStatusText = orderStatusText;
    }
}

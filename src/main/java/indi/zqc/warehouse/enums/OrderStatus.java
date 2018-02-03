package indi.zqc.warehouse.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * Title : OrderStatus.java
 * Package : indi.zqc.warehouse.enums
 * Description : 订单状态
 * Create on : 2018/2/2 15:10
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public enum OrderStatus {

    CREATED("created", "创建"),

    PRODUCED("produced", "已生成"),

    FINISHED("finished", "完成");

    private String key;

    private String value;

    OrderStatus(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static String getValue(String key) {
        OrderStatus[] orderStatuses = values();
        for (OrderStatus orderStatus : orderStatuses) {
            if (StringUtils.equals(orderStatus.getKey(), key)) {
                return orderStatus.getValue();
            }
        }
        return null;
    }
}

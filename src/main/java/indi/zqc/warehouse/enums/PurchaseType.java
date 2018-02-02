package indi.zqc.warehouse.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * Title : PurchaseStatus.java
 * Package : indi.zqc.warehouse.enums
 * Description : 采购类型
 * Create on : 2018/2/1 17:13
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public enum PurchaseType {

    MANUAL("manual", "手动"),

    AUTO("auto", "自动");

    private String key;

    private String value;

    PurchaseType(String key, String value) {
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
        if (StringUtils.equals(MANUAL.getKey(), key)) {
            return MANUAL.getValue();
        } else if (StringUtils.equals(AUTO.getKey(), key)) {
            return AUTO.getValue();
        }
        return null;
    }
}

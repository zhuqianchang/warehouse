package indi.zqc.warehouse.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * Title : OperationType.java
 * Package : indi.zqc.warehouse.enums
 * Description : 出入库类型
 * Create on : 2018/1/29 20:23
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public enum OperationType {

    INPUT("input", "入库"),

    OUTPUT("output", "出库");

    private String key;

    private String value;

    OperationType(String key, String value) {
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
        if (StringUtils.equals(INPUT.getKey(), key)) {
            return INPUT.getValue();
        } else if (StringUtils.equals(OUTPUT.getKey(), key)) {
            return OUTPUT.getValue();
        }
        return null;
    }

}

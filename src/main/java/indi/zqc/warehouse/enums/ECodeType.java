package indi.zqc.warehouse.enums;

/**
 * Title : ECodeType.java
 * Package : indi.zqc.warehouse.enums
 * Description : 枚举码类型
 * Create on : 2018/1/28 13:23
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public enum ECodeType {

    MATERIAL("material", "物料类型");

    private String key;

    private String value;

    ECodeType(String key, String value) {
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
}

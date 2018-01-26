package indi.zqc.warehouse.enums;

/**
 * Title : WarehouseType.java
 * Package : indi.zqc.warehouse.constant
 * Description : 仓库类型
 * Create on : 2018/1/23 15:10
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public enum WarehouseType {

    MATERIAL("material", "物料库"),
    PART("part", "配件库"),
    SEMIS("semis", "半成品库"),
    PRODUCT("product", "成品库"),
    REJECT("reject", "废品库");

    private String key;

    private String value;

    WarehouseType(String key, String value) {
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

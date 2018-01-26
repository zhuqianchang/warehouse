package indi.zqc.warehouse.enums;

/**
 * Title : ResourceType.java
 * Package : indi.zqc.warehouse.enums
 * Description : 菜单类型
 * Create on : 2018/1/26 14:53
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public enum MenuType {

    //窗体
    FORM("FORM"),

    //按钮
    BUTN("BUTN"),

    //模块
    MODULE("MODULE");

    private String value;

    private MenuType(String code) {
        this.value = code;
    }

    public String getValue() {
        return value;
    }

}

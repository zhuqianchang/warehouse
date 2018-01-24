package indi.zqc.warehouse.enums;

/**
 * Title : DwzCallbackType.java
 * Package : indi.zqc.warehouse.enums
 * Description : DWZ回调类型
 * Create on : 2018/1/24 20:19
 *
 * @author zhu.qianchang
 * @version v1.0.0
 */
public enum DWZCallbackType {

    //无操作
    EMPTY(""),

    //关闭当前tab
    CLOSECURRENT("closeCurrent"),

    //页面跳转,配合forwardUrl
    FORWARD("forward");

    private String value;

    private DWZCallbackType(String type) {
        this.value = type;
    }

    public String getValue() {
        return value;
    }
}

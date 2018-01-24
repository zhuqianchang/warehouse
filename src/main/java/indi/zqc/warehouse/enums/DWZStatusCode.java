package indi.zqc.warehouse.enums;

/**
 * Title : DwzStatusCode.java
 * Package : indi.zqc.warehouse.enums
 * Description : DWZ异步请求响应码
 * Create on : 2018/1/24 20:11
 *
 * @author zhu.qianchang
 * @version v1.0.0
 */
public enum DWZStatusCode {

    OK(200, "成功"),
    ERROR(300, "失败"),
    TIMEOUT(301, "超时");

    private int key;

    private String value;

    DWZStatusCode(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

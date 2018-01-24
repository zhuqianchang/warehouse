package indi.zqc.warehouse.model;

/**
 * Title : DWZResult.java
 * Package : indi.zqc.warehouse.model
 * Description : DWZ返回结果
 * Create on : 2018/1/24 20:14
 *
 * @author zhu.qianchang
 * @version v1.0.0
 */
public class DWZResult {

    //状态码
    private int statusCode;

    //提示信息
    private String message;

    //窗体ID
    private String navTabId;

    //回调类型
    private String callbackType;

    //跳转地址
    private String forwardUrl;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNavTabId() {
        return navTabId;
    }

    public void setNavTabId(String navTabId) {
        this.navTabId = navTabId;
    }

    public String getCallbackType() {
        return callbackType;
    }

    public void setCallbackType(String callbackType) {
        this.callbackType = callbackType;
    }

    public String getForwardUrl() {
        return forwardUrl;
    }

    public void setForwardUrl(String forwardUrl) {
        this.forwardUrl = forwardUrl;
    }
}

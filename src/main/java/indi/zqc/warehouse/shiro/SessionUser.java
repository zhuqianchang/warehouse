package indi.zqc.warehouse.shiro;

/**
 * Title : SessionUser.java
 * Package : indi.zqc.warehouse.shiro
 * Description : 权限用户
 * Create on : 2018/1/26 11:00
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class SessionUser {

    //会话凭证
    private String ticket;

    //用户编号
    private String userCode;

    private String userText;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserText() {
        return userText;
    }

    public void setUserText(String userText) {
        this.userText = userText;
    }
}

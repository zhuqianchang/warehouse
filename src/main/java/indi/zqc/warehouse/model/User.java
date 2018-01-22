package indi.zqc.warehouse.model;

/**
 * Title : User.java
 * Package : indi.zqc.warehouse.model
 * Description : 用户
 * Create on : 2018/1/22 10:03
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class User extends Common {

    private String userCode;

    private String userText;

    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userCode='" + userCode + '\'' +
                ", userText='" + userText + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

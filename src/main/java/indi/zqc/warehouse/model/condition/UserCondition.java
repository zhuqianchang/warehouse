package indi.zqc.warehouse.model.condition;

/**
 * Title : UserCondition.java
 * Package : indi.zqc.warehouse.model.condition
 * Description : 用户查询条件
 * Create on : 2018/1/22 10:09
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class UserCondition extends Condition {

    private String userCode;

    private String userText;

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

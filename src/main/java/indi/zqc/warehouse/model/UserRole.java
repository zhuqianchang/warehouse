package indi.zqc.warehouse.model;

/**
 * Title : UserRole.java
 * Package : indi.zqc.warehouse.model
 * Description : 用户角色
 * Create on : 2018/1/23 14:36
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class UserRole extends Common {

    //用户编号
    private String userCode;

    //角色编号
    private String roleCode;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
}

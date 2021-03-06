package indi.zqc.warehouse.model.condition;

import org.apache.commons.lang3.StringUtils;

/**
 * Title : RoleCondition.java
 * Package : indi.zqc.warehouse.model.condition
 * Description : 角色查询条件
 * Create on : 2018/1/26 15:01
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class RoleCondition extends Condition {

    //角色编号
    private String roleCode;

    //角色描述
    private String roleText;

    public String getRoleCode() {
        return StringUtils.upperCase(roleCode);
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = StringUtils.upperCase(roleCode);
    }

    public String getRoleText() {
        return roleText;
    }

    public void setRoleText(String roleText) {
        this.roleText = roleText;
    }
}

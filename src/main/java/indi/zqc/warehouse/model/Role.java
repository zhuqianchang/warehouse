package indi.zqc.warehouse.model;

import org.apache.commons.lang3.StringUtils;

/**
 * Title : Role.java
 * Package : indi.zqc.warehouse.model
 * Description : 角色
 * Create on : 2018/1/23 14:27
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class Role extends Common {

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

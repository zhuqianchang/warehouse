package indi.zqc.warehouse.model;

/**
 * Title : RoleMenu.java
 * Package : indi.zqc.warehouse.model
 * Description : 权限菜单
 * Create on : 2018/1/29 14:48
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class RoleMenu {

    private String roleCode;

    private String menuCode;

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }
}

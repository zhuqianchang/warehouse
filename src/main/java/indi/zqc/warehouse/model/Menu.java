package indi.zqc.warehouse.model;

import indi.zqc.warehouse.enums.MenuType;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Title : Menu.java
 * Package : indi.zqc.warehouse.model
 * Description : 菜单
 * Create on : 2018/1/26 16:28
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class Menu extends Common implements TreeInterface{

    //菜单编号
    private String menuCode;

    //菜单描述
    private String menuText;

    //父菜单编号
    private String parentMenuCode;

    //菜单URL
    private String webUrl;

    //菜单类型
    private String menuType;

    //序号
    private Integer displayOrdinal;

    //选中状态
    private Integer isChecked;

    //子菜单
    private List<Menu> children = new ArrayList<>();

    public String getMenuCode() {
        return StringUtils.upperCase(menuCode);
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = StringUtils.upperCase(menuCode);
    }

    public String getMenuText() {
        return menuText;
    }

    public void setMenuText(String menuText) {
        this.menuText = menuText;
    }

    public String getParentMenuCode() {
        return parentMenuCode;
    }

    public void setParentMenuCode(String parentMenuCode) {
        this.parentMenuCode = parentMenuCode;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public Integer getDisplayOrdinal() {
        return displayOrdinal;
    }

    public void setDisplayOrdinal(Integer displayOrdinal) {
        this.displayOrdinal = displayOrdinal;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    @Override
    public void setTrees(List trees) {
        this.children = trees;
    }

    @Override
    public String getPid() {
        return this.parentMenuCode;
    }

    @Override
    public String getNodeId() {
        return this.menuCode;
    }

    @Override
    public boolean getIsLeaf() {
        if (StringUtils.isNotBlank(menuType)) {
            return StringUtils.equals(MenuType.FORM.getValue(), menuType);
        }
        return false;
    }

    @Override
    public Integer getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(Integer isChecked) {
        this.isChecked = isChecked;
    }
}

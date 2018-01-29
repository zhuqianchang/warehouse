package indi.zqc.warehouse.controller;

import com.github.pagehelper.Page;
import indi.zqc.warehouse.constant.Constants;
import indi.zqc.warehouse.model.DWZResult;
import indi.zqc.warehouse.model.Role;
import indi.zqc.warehouse.model.condition.RoleCondition;
import indi.zqc.warehouse.service.MenuService;
import indi.zqc.warehouse.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Title : RoleController.java
 * Package : indi.zqc.warehouse.controller
 * Description : 角色
 * Create on : 2018/1/27 10:43
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
@Controller
@RequestMapping(value = "/role/*")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @RequestMapping("/list")
    public String roleList(Model model, RoleCondition condition, String navTabId) {
        Page<Role> page = roleService.selectRolePage(condition);
        condition.setData(page);
        condition.setTotalCount(page.getTotal());
        model.addAttribute(PAGE, condition);
        model.addAttribute(NAVTABID, navTabId);
        return "role/role_list";
    }

    @RequestMapping("/add")
    public String addRole(Model model, String navTabId) {
        model.addAttribute(NAVTABID, navTabId);
        return "role/role_add";
    }

    @RequestMapping("/edit")
    public String editRole(Model model, String roleCode, String navTabId) {
        Role role = roleService.selectRole(roleCode);
        model.addAttribute(NAVTABID, navTabId);
        model.addAttribute("role", role);
        return "role/role_add";
    }

    @RequestMapping("/view")
    public String viewRole(Model model, String roleCode, String navTabId) {
        Role role = roleService.selectRole(roleCode);
        model.addAttribute(NAVTABID, navTabId);
        model.addAttribute("role", role);
        model.addAttribute("menuTree", menuService.selectMenuByRole(roleCode));
        return "role/role_view";
    }

    @RequestMapping("/save")
    @ResponseBody
    public DWZResult saveRole(Role role, String navTabId, HttpServletRequest request) {
        Role oldRole = roleService.selectRole(role.getRoleCode());
        if (oldRole == null) {
            setCreateInfo(role);
            roleService.insertRole(role);
        } else {
            setModifyInfo(role);
            roleService.updateRole(role);
        }
        String forwardUrl = getForwardUrl(request) + "/role/list?navTabId=" + navTabId;
        return dialogAjaxDone(navTabId, forwardUrl);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public DWZResult deleteRole(String roleCodes) {
        roleService.batchDeleteRole(roleCodes);
        return ajaxDone();
    }

    @RequestMapping("/verify")
    @ResponseBody
    public boolean verifyRoleCode(String roleCode) {
        return roleService.selectRole(roleCode) == null;
    }

    @RequestMapping("/lookup")
    public String roleLookup(Model model, RoleCondition condition) {
        Page<Role> page = roleService.selectRolePage(condition);
        condition.setData(page);
        condition.setTotalCount(page.getTotal());
        model.addAttribute(PAGE, condition);
        return "role/role_lookup";
    }

    @RequestMapping("/authorize")
    public String authorizeRole(Model model, String roleCode, String navTabId) {
        model.addAttribute("role", roleService.selectRole(roleCode));
        model.addAttribute("menuTree", menuService.selectMenuByRole(roleCode));
        model.addAttribute(NAVTABID, navTabId);
        return "role/role_authorize";
    }

    @RequestMapping("/authorize/save")
    @ResponseBody
    public DWZResult saveAuthorize(String roleCode, String menuCodes) {
        roleService.authorize(roleCode, menuCodes.split(Constants.SEPARATOR));
        return dialogAjaxDone();
    }
}

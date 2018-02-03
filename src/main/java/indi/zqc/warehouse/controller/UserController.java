package indi.zqc.warehouse.controller;

import com.github.pagehelper.Page;
import indi.zqc.warehouse.constant.Constants;
import indi.zqc.warehouse.model.DWZResult;
import indi.zqc.warehouse.model.User;
import indi.zqc.warehouse.model.condition.UserCondition;
import indi.zqc.warehouse.service.RoleService;
import indi.zqc.warehouse.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Title : UserController.java
 * Package : indi.zqc.warehouse.controller
 * Description : 用户
 * Create on : 2018/1/22 14:35
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
@Controller
@RequestMapping(value = "/user/*")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("/list")
    public String userList(Model model, UserCondition condition, String navTabId) {
        Page<User> page = userService.selectUserPage(condition);
        condition.setData(page);
        condition.setTotalCount(page.getTotal());
        model.addAttribute(PAGE, condition);
        model.addAttribute("defaultPassword", Constants.DEFAULT_PASSWORD);
        model.addAttribute(NAVTABID, navTabId);
        return "user/user_list";
    }

    @RequestMapping("/add")
    public String addUser(Model model, String navTabId) {
        model.addAttribute("defaultPassword", Constants.DEFAULT_PASSWORD);
        model.addAttribute(NAVTABID, navTabId);
        return "user/user_add";
    }

    @RequestMapping("/edit")
    public String editUser(Model model, String userCode, String navTabId) {
        model.addAttribute("user", userService.selectUser(userCode));
        model.addAttribute("roles", roleService.selectRoleByUser(userCode));
        model.addAttribute(NAVTABID, navTabId);
        return "user/user_add";
    }

    @RequestMapping("/view")
    public String viewUser(Model model, String userCode, String navTabId) {
        model.addAttribute("user", userService.selectUser(userCode));
        model.addAttribute("roles", roleService.selectRoleByUser(userCode));
        model.addAttribute(NAVTABID, navTabId);
        return "user/user_view";
    }

    @RequestMapping("/save")
    @ResponseBody
    public DWZResult saveUser(User user, String roleCodes, String navTabId, HttpServletRequest request) throws Exception {
        User oldUser = userService.selectUser(user.getUserCode());
        if (oldUser == null) {
            setCreateInfo(user);
            userService.insertUser(user);
        } else {
            setModifyInfo(user);
            userService.updateUser(user);
        }
        if (StringUtils.isNotBlank(roleCodes)) {
            userService.updateUserRole(user.getUserCode(), roleCodes);
        }
        String forwardUrl = getForwardUrl(request) + "/user/list?navTabId=" + navTabId;
        return dialogAjaxDone(navTabId, forwardUrl);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public DWZResult deleteUser(String userCode) {
        userService.deleteUser(userCode);
        return ajaxDone();
    }

    @RequestMapping("/reset")
    @ResponseBody
    public DWZResult resetUser(String userCode) throws Exception {
        userService.resetUser(userCode);
        return ajaxDone();
    }

    @RequestMapping("/changePwd")
    public String changePwd(){
        return "changePwd";
    }

    @RequestMapping("/password/save")
    @ResponseBody
    public DWZResult changePwd(String oldPassword, String newPassword) throws Exception {
        userService.changePwd(getCurrentUserCode(), oldPassword, newPassword);
        return ajaxDone();
    }

    @RequestMapping("/verify")
    @ResponseBody
    public boolean verifyUserCode(String userCode) {
        return userService.selectUser(userCode) == null;
    }
}

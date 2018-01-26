package indi.zqc.warehouse.controller;

import com.github.pagehelper.Page;
import indi.zqc.warehouse.model.DWZResult;
import indi.zqc.warehouse.model.User;
import indi.zqc.warehouse.model.condition.UserCondition;
import indi.zqc.warehouse.service.UserService;
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

    @RequestMapping("/list")
    public String userList(Model model, UserCondition condition, String navTabId) {
        Page<User> page = userService.selectUserPage(condition);
        condition.setData(page);
        condition.setTotalCount(page.getTotal());
        model.addAttribute(PAGE, condition);
        model.addAttribute(NAVTABID, navTabId);
        return "user/user_list";
    }

    @RequestMapping("/add")
    public String addUser(Model model, String navTabId) {
        model.addAttribute(NAVTABID, navTabId);
        return "user/user_add";
    }

    @RequestMapping("/edit")
    public String editUser(Model model, String userCode, String navTabId) {
        User user = userService.selectUser(userCode);
        model.addAttribute(NAVTABID, navTabId);
        model.addAttribute("user", user);
        return "user/user_add";
    }

    @RequestMapping("/view")
    public String viewUser(Model model, String userCode, String navTabId) {
        User user = userService.selectUser(userCode);
        model.addAttribute(NAVTABID, navTabId);
        model.addAttribute("user", user);
        return "user/user_view";
    }

    @RequestMapping("/save")
    @ResponseBody
    public DWZResult saveUser(User user, String navTabId, HttpServletRequest request) {
        User oldUser = userService.selectUser(user.getUserCode());
        if (oldUser == null) {
            setCreateInfo(user);
            userService.insertUser(user);
        } else {
            setModifyInfo(user);
            userService.updateUser(user);
        }
        String forwardUrl = getForwardUrl(request) + "/userParameter/userParameterList?navTabId=" + navTabId;
        return dialogAjaxDone(navTabId, forwardUrl);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public DWZResult deleteUser(String userCodes, String navTabId) {
        userService.batchDeleteUser(userCodes);
        return navTabAjaxDone(navTabId);
    }

    @RequestMapping("/verify")
    @ResponseBody
    public boolean verifyUserCode(String userCode) {
        return userService.selectUser(userCode) == null;
    }
}

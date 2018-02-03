package indi.zqc.warehouse.controller;

import indi.zqc.warehouse.shiro.AuthUtils;
import indi.zqc.warehouse.shiro.SessionUser;
import indi.zqc.warehouse.util.EncryptAlgorithm;
import indi.zqc.warehouse.util.SecurityContextUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Title : LoginController.java
 * Package : indi.zqc.warehouse.controller
 * Description : 登录
 * Create on : 2018/1/25 17:14
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
@Controller
@RequestMapping
public class LoginController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(LoggerFactory.class);

    private static final String MESSAGE = "message";

    @RequestMapping("/login")
    public String login() {
        SessionUser sessionUser = SecurityContextUtils.getCurrentUser();
        //用户已登录，则直接到主页
        if (sessionUser != null) {
            return "redirect:/index";
        }
        return "login";
    }

    @Valid
    @RequestMapping(value = "/doLogin")
    public String doLogin(Model model, @NotNull String userCode, @NotNull String password) throws Exception {
        try {
            model.addAttribute("userCode", userCode);
            model.addAttribute("password", password);
            password = EncryptAlgorithm.hexMD5(password);
            SessionUser sessionUser = (SessionUser) AuthUtils.login(StringUtils.upperCase(userCode), password);
            sessionUser.setTicket(AuthUtils.getSessionId());
        } catch (Exception e) {
            //登录失败返回登录页面，并附带错误信息
            logger.error(e.getCause().getMessage(), e.getCause());
            model.addAttribute(MESSAGE, e.getCause().getMessage());
            return "login";
        }
        return "redirect:/index";
    }

    @RequestMapping(value = "logout")
    public String logout() {
        SessionUser sessionUser = (SessionUser) AuthUtils.getSessionUser();
        if (sessionUser != null) {
            //登出
            AuthUtils.logout();
        }
        return "login";
    }
}

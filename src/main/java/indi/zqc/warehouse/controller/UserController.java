package indi.zqc.warehouse.controller;

import indi.zqc.warehouse.model.User;
import indi.zqc.warehouse.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public String select(String userCode) {
        User user = userService.selectUser(userCode);
        return user.toString();
    }
}

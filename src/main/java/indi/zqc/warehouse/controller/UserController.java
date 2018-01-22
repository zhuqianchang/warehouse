package indi.zqc.warehouse.controller;

import indi.zqc.warehouse.model.User;
import indi.zqc.warehouse.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController(value = "/user/*")
public class UserController {

    @Resource
    private UserService userService;


    @RequestMapping(method = RequestMethod.GET)
    public String select(String userCode) {
        User user = userService.selectUser(userCode);
        return user.toString();
    }
}

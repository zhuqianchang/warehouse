package indi.zqc.warehouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Title : IndexController.java
 * Package : indi.zqc.warehouse.controller
 * Description : 测试
 * Create on : 2018/1/22 9:30
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
@Controller
@RequestMapping
public class IndexController extends BaseController{

    @RequestMapping(value = "index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "login")
    public String login() {
        return "login";
    }
}

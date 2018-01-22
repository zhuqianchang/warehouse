package indi.zqc.warehouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class IndexController {

    @RequestMapping
    public String index(Model model, String message) {
        model.addAttribute("message", message);
        return "index";
    }
}

package indi.zqc.warehouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Title : ManageController.java
 * Package : indi.zqc.warehouse.controller
 * Description : 管理
 * Create on : 2018/2/5 10:11
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
@Controller
@RequestMapping
public class ManageController {

    @RequestMapping(value = "manage")
    public String index() {
        return "manage";
    }

}

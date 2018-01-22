package indi.zqc.warehouse.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Title : IndexController.java
 * Package : indi.zqc.warehouse.controller
 * Description :
 * Create on : 2018/1/22 9:30
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
@RestController
public class IndexController {

    @RequestMapping("/")
    public String index() {
        return "Hello World!";
    }
}

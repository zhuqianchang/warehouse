package indi.zqc.warehouse.controller;

import indi.zqc.warehouse.constant.Constants;
import indi.zqc.warehouse.service.MenuService;
import indi.zqc.warehouse.shiro.SessionUser;
import indi.zqc.warehouse.util.SecurityContextUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class IndexController extends BaseController {

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "index")
    public String index(Model model) {
        SessionUser user = SecurityContextUtils.getCurrentUser();
        model.addAttribute("user", user);
        if (StringUtils.equals(Constants.ADMIN, user.getUserCode())) {
            model.addAttribute("menuTree", menuService.selectMenuTree());
        } else {
            model.addAttribute("menuTree", menuService.selectMenuByUser(user.getUserCode()));
        }
        return "index";
    }
}

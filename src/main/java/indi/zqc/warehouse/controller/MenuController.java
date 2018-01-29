package indi.zqc.warehouse.controller;

import indi.zqc.warehouse.enums.DWZCallbackType;
import indi.zqc.warehouse.model.DWZResult;
import indi.zqc.warehouse.model.Menu;
import indi.zqc.warehouse.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Title : MenuController.java
 * Package : indi.zqc.warehouse.controller
 * Description : 菜单
 * Create on : 2018/1/26 16:43
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
@Controller
@RequestMapping("/menu/*")
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/tree")
    public String menuTree(Model model, String navTabId, String selectNode) {
        model.addAttribute(NAVTABID, navTabId);
        model.addAttribute("selectNode", selectNode);
        model.addAttribute("menuTree", menuService.selectMenuTree());
        return "menu/menu_tree";
    }

    @RequestMapping("/edit")
    public String editMenu(Model model, String menuCode) {
        model.addAttribute("menu", menuService.selectMenu(menuCode));
        return "menu/menu_edit";
    }

    @RequestMapping("/view")
    public String viewMenu(Model model, String menuCode) {
        model.addAttribute("menu", menuService.selectMenu(menuCode));
        return "menu/menu_view";
    }

    @RequestMapping("/save")
    @ResponseBody
    public DWZResult saveMenu(String navTabId, Menu menu, HttpServletRequest request) {
        setModifyInfo(menu);
        menuService.updateMenu(menu);
        String forwardUrl = getForwardUrl(request) + "/menu/tree?navTabId=" + navTabId
                + "&selectNode=" + menu.getMenuCode();
        return navTabAjaxDone(navTabId, DWZCallbackType.FORWARD, forwardUrl);
    }

    @RequestMapping("/up")
    @ResponseBody
    public DWZResult upMenu(String navTabId, String menuCode, HttpServletRequest request) {
        menuService.upMenu(menuCode);
        String forwardUrl = getForwardUrl(request) + "/menu/tree?navTabId=" + navTabId
                + "&selectNode=" + menuCode;
        return navTabAjaxDone(navTabId, DWZCallbackType.FORWARD, forwardUrl);
    }

    @RequestMapping("/down")
    @ResponseBody
    public DWZResult downMenu(String navTabId, String menuCode, HttpServletRequest request) {
        menuService.downMenu(menuCode);
        String forwardUrl = getForwardUrl(request) + "/menu/tree?navTabId=" + navTabId
                + "&selectNode=" + menuCode;
        return navTabAjaxDone(navTabId, DWZCallbackType.FORWARD, forwardUrl);
    }

}

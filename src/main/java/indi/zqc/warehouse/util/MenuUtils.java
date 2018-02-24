package indi.zqc.warehouse.util;

import indi.zqc.warehouse.model.Menu;
import indi.zqc.warehouse.service.MenuService;
import org.apache.commons.lang3.StringUtils;

/**
 * Title : MenuUtils.java
 * Package : indi.zqc.warehouse.util
 * Description : 菜单工具
 * Create on : 2018/2/24 13:51
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class MenuUtils {

    private static MenuService menuService;

    public MenuUtils(MenuService menuService) {
        MenuUtils.menuService = menuService;
    }

    public static boolean containMenu(String menuCode) {
        if (StringUtils.isBlank(menuCode)) {
            return false;
        }
        String userCode = SecurityContextUtils.getCurrentUserCode();
        if (StringUtils.isBlank(userCode)) {
            return false;
        }
        Menu menu = menuService.selectMenuByCodeAndUser(menuCode, userCode);
        if (menu == null) {
            return false;
        }
        return true;
    }
}

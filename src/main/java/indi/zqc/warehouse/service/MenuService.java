package indi.zqc.warehouse.service;

import indi.zqc.warehouse.model.Menu;
import indi.zqc.warehouse.model.TreeInterface;

import java.util.List;

/**
 * Title : MenuService.java
 * Package : indi.zqc.warehouse.service
 * Description : 菜单服务接口
 * Create on : 2018/1/26 16:50
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public interface MenuService {

    int insertMenu(Menu menu);

    int deleteMenu(String menuCode);

    int updateMenu(Menu menu);

    Menu selectMenu(String menuCode);

    List<TreeInterface> selectMenuTree();

    int selectMaxOrdinal(String parentMenuCode);

    int selectRootMaxOrdinal();

    List<Menu> selectMenuByParent(String parentMenuCode);

    List<Menu> selectRootMenu();
}

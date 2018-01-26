package indi.zqc.warehouse.dao;

import indi.zqc.warehouse.model.Menu;

import java.util.List;

/**
 * Title : MenuDao.java
 * Package : indi.zqc.warehouse.dao
 * Description : 菜单DAO
 * Create on : 2018/1/26 16:54
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public interface MenuDao {

    int insertMenu(Menu menu);

    int deleteMenu(String menuCode);

    int updateMenu(Menu menu);

    Menu selectMenu(String menuCode);

    List<Menu> selectMenuList();

    int selectMaxOrdinal(String parentMenuCode);

    int selectRootMaxOrdinal();

    List<Menu> selectMenuByParent(String parentMenuCode);

    List<Menu> selectRootMenu();
}

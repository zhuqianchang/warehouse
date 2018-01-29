package indi.zqc.warehouse.dao;

import indi.zqc.warehouse.model.Menu;
import org.apache.ibatis.annotations.Param;

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

    int deleteMenu(@Param("menuCode") String menuCode);

    int updateMenu(Menu menu);

    Menu selectMenu(@Param("menuCode") String menuCode);

    List<Menu> selectMenuList();

    int selectMaxOrdinal(@Param("parentMenuCode") String parentMenuCode);

    int selectRootMaxOrdinal();

    List<Menu> selectMenuByParent(@Param("parentMenuCode") String parentMenuCode);

    List<Menu> selectRootMenu();

    List<Menu> selectMenuByRole(@Param("roleCode") String roleCode);

    List<Menu> selectMenuByUser(@Param("userCode") String userCode);
}

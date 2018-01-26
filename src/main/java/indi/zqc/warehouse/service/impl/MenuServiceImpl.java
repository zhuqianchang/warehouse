package indi.zqc.warehouse.service.impl;

import indi.zqc.warehouse.dao.MenuDao;
import indi.zqc.warehouse.model.Menu;
import indi.zqc.warehouse.model.TreeInterface;
import indi.zqc.warehouse.service.MenuService;
import indi.zqc.warehouse.util.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Title : MenuServiceImpl.java
 * Package : indi.zqc.warehouse.service.impl
 * Description : 菜单服务实现
 * Create on : 2018/1/26 17:00
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public int insertMenu(Menu menu) {
        return menuDao.insertMenu(menu);
    }

    @Override
    public int deleteMenu(String menuCode) {
        return menuDao.deleteMenu(menuCode);
    }

    @Override
    public int updateMenu(Menu menu) {
        return menuDao.updateMenu(menu);
    }

    @Override
    public Menu selectMenu(String menuCode) {
        return menuDao.selectMenu(menuCode);
    }

    @Override
    public List<TreeInterface> selectMenuTree() {
        List<Menu> menuList = menuDao.selectMenuList();
        return TreeUtils.gradingRecursion(menuList.toArray(new Menu[menuList.size()]), null);
    }

    @Override
    public int selectMaxOrdinal(String parentMenuCode) {
        return menuDao.selectMaxOrdinal(parentMenuCode);
    }

    @Override
    public int selectRootMaxOrdinal() {
        return menuDao.selectRootMaxOrdinal();
    }

    @Override
    public List<Menu> selectMenuByParent(String parentMenuCode) {
        return menuDao.selectMenuByParent(parentMenuCode);
    }

    @Override
    public List<Menu> selectRootMenu() {
        return menuDao.selectRootMenu();
    }
}

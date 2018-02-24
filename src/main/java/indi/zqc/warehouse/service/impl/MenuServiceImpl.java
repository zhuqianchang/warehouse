package indi.zqc.warehouse.service.impl;

import indi.zqc.warehouse.dao.MenuDao;
import indi.zqc.warehouse.exception.BusinessException;
import indi.zqc.warehouse.model.Menu;
import indi.zqc.warehouse.model.TreeInterface;
import indi.zqc.warehouse.service.MenuService;
import indi.zqc.warehouse.util.TreeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public int updateMenu(Menu menu) {
        return menuDao.updateMenu(menu);
    }

    @Override
    public Menu selectMenu(String menuCode) {
        if(StringUtils.isBlank(menuCode)){
            throw new BusinessException("菜单编号不能为空");
        }
        Menu menu = menuDao.selectMenu(menuCode);
        if (menu == null) {
            throw new BusinessException("菜单不存在");
        }
        return menu;
    }

    @Override
    public List<TreeInterface> selectMenuTree() {
        List<Menu> menuList = menuDao.selectMenuList();
        return TreeUtils.gradingRecursion(menuList.toArray(new Menu[menuList.size()]), null);
    }

    @Override
    public List<TreeInterface> selectMenuByRole(String roleCode){
        List<Menu> menuList = menuDao.selectMenuByRole(roleCode);
        return TreeUtils.gradingRecursion(menuList.toArray(new Menu[menuList.size()]), null);
    }

    @Override
    public List<TreeInterface> selectMenuByUser(String userCode){
        List<Menu> menuList = menuDao.selectMenuByUser(userCode);
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

    @Override
    public Menu selectMenuByCodeAndUser(String menuCode, String userCode) {
        return menuDao.selectMenuByCodeAndUser(menuCode, userCode);
    }

    @Override
    public void upMenu(String menuCode) {
        Menu menu = menuDao.selectMenu(menuCode);
        //重置同级别的菜单序号
        List<Menu> mlist = siblings(menu);
        for (int i = 0; i < mlist.size(); i++) {
            mlist.get(i).setDisplayOrdinal(i + 1);
            updateMenu(mlist.get(i));
        }
        for (int i = 0; i < mlist.size(); i++) {
            if (mlist.get(i).getMenuCode().equals(menuCode)) {
                if (i == 0) {
                    throw new BusinessException("顺序号已经调至最小，无法继续上移");
                } else {
                    //交换顺序
                    Menu menuUp = mlist.get(i - 1);
                    int ordinal = menu.getDisplayOrdinal();
                    menu.setDisplayOrdinal(menuUp.getDisplayOrdinal());
                    menuUp.setDisplayOrdinal(ordinal);
                    updateMenu(menu);
                    updateMenu(menuUp);
                }
                break;
            }
        }
    }

    @Override
    public void downMenu(String menuCode) {
        Menu menu = menuDao.selectMenu(menuCode);
        //重置同级别的菜单序号
        List<Menu> mlist = siblings(menu);
        for (int i = 0; i < mlist.size(); i++) {
            mlist.get(i).setDisplayOrdinal(i + 1);
            updateMenu(mlist.get(i));
        }
        for (int i = 0; i < mlist.size(); i++) {
            if (mlist.get(i).getMenuCode().equals(menuCode)) {
                if (i == mlist.size() - 1) {
                    throw new BusinessException("顺序号已经调至最大，无法继续下移");
                } else {
                    //交换顺序
                    Menu menuUp = mlist.get(i + 1);
                    int ordinal = menu.getDisplayOrdinal();
                    menu.setDisplayOrdinal(menuUp.getDisplayOrdinal());
                    menuUp.setDisplayOrdinal(ordinal);
                    updateMenu(menu);
                    updateMenu(menuUp);
                }
                break;
            }
        }
    }

    /**
     * 获得同级菜单
     */
    public List<Menu> siblings(Menu menu) {
        if (StringUtils.isBlank(menu.getParentMenuCode())) {
            return menuDao.selectRootMenu();
        } else {
            return menuDao.selectMenuByParent(menu.getParentMenuCode());
        }
    }
}

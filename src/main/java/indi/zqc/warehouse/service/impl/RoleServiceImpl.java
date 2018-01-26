package indi.zqc.warehouse.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import indi.zqc.warehouse.dao.RoleDao;
import indi.zqc.warehouse.model.Role;
import indi.zqc.warehouse.model.condition.RoleCondition;
import indi.zqc.warehouse.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Title : RoleServiceImpl.java
 * Package : indi.zqc.warehouse.service.impl
 * Description : 角色服务实现
 * Create on : 2018/1/26 15:09
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public int insertRole(Role role) {
        return roleDao.insertRole(role);
    }

    @Override
    public int deleteRole(String roleCode) {
        return roleDao.deleteRole(roleCode);
    }

    @Override
    public int batchDeleteRole(String roleCodes) {
        int deleteNum = 0;
        for (String roleCode : roleCodes.split(",")) {
            deleteNum += deleteRole(roleCode);
        }
        return deleteNum;
    }

    @Override
    public int updateRole(Role role) {
        return roleDao.updateRole(role);
    }

    @Override
    public Role selectRole(String roleCode) {
        return roleDao.selectRole(roleCode);
    }

    @Override
    public Page<Role> selectRolePage(RoleCondition condition) {
        PageHelper.startPage(condition.getPageNum(), condition.getNumPerPage());
        return roleDao.selectRolePage(condition);
    }
}

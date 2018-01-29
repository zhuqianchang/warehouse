package indi.zqc.warehouse.service;

import com.github.pagehelper.Page;
import indi.zqc.warehouse.model.Role;
import indi.zqc.warehouse.model.condition.RoleCondition;

import java.util.List;

/**
 * Title : RoleService.java
 * Package : indi.zqc.warehouse.service
 * Description : 用户服务接口
 * Create on : 2018/1/22 10:10
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public interface RoleService {

    int insertRole(Role role);

    int deleteRole(String roleCode);

    int batchDeleteRole(String roleCodes);

    int updateRole(Role role);

    Role selectRole(String roleCode);

    Page<Role> selectRolePage(RoleCondition condition);

    int authorize(String roleCode, String[] menuCodes);

    List<Role> selectRoleByUser(String userCode);
}

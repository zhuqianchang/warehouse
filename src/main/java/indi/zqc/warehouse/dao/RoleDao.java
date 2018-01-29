package indi.zqc.warehouse.dao;

import com.github.pagehelper.Page;
import indi.zqc.warehouse.model.Role;
import indi.zqc.warehouse.model.condition.RoleCondition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Title : RoleDao.java
 * Package : indi.zqc.warehouse.dao
 * Description : 角色DAO
 * Create on : 2018/1/26 15:07
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public interface RoleDao {

    int insertRole(Role role);

    int deleteRole(@Param("roleCode") String roleCode);

    int updateRole(Role role);

    Role selectRole(@Param("roleCode") String roleCode);

    Page<Role> selectRolePage(RoleCondition condition);

    List<Role> selectRoleByUser(@Param("userCode") String userCode);
}

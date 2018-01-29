package indi.zqc.warehouse.dao;

import org.apache.ibatis.annotations.Param;

/**
 * Title : RoleMenuDao.java
 * Package : indi.zqc.warehouse.dao
 * Description : 权限菜单DAO
 * Create on : 2018/1/29 14:49
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public interface RoleMenuDao {

    int insertRoleMenu(@Param("roleCode") String roleCode, @Param("menuCode") String menuCode);

    int deleteRoleMenuByRole(@Param("roleCode") String roleCode);
}

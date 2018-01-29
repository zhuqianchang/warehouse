package indi.zqc.warehouse.dao;

import org.apache.ibatis.annotations.Param;

/**
 * Title : UserRoleDao.java
 * Package : indi.zqc.warehouse.dao
 * Description : 用户角色DAO
 * Create on : 2018/1/29 18:36
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public interface UserRoleDao {

    int insertUserRole(@Param("userCode") String userCode, @Param("roleCode") String roleCode);

    int deleteUserRoleByUser(@Param("userCode") String userCode);
}

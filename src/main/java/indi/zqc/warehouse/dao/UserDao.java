package indi.zqc.warehouse.dao;

import com.github.pagehelper.Page;
import indi.zqc.warehouse.model.User;
import indi.zqc.warehouse.model.condition.UserCondition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Title : UserDao.java
 * Package : indi.zqc.warehouse.dao
 * Description : 用户DAO
 * Create on : 2018/1/22 10:02
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public interface UserDao {

    int insertUser(User user);

    int deleteUser(@Param("userCode") String userCode);

    int updateUser(User user);

    int resetPassword(User user);

    User selectUser(@Param("userCode") String userCode);

    List<User> selectUserByRole(@Param("roleCode") String roleCode);

    Page<User> selectUserPage(UserCondition condition);
}

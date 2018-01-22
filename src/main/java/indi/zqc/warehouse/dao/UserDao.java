package indi.zqc.warehouse.dao;

import com.github.pagehelper.Page;
import indi.zqc.warehouse.model.User;
import indi.zqc.warehouse.model.condition.UserCondition;
import org.apache.ibatis.annotations.Param;

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

    User selectUser(@Param("userCode") String userCode);

    Page<User> selectUserPage(UserCondition condition);
}

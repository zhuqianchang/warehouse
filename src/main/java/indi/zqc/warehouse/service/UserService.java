package indi.zqc.warehouse.service;

import com.github.pagehelper.Page;
import indi.zqc.warehouse.model.User;
import indi.zqc.warehouse.model.condition.UserCondition;

/**
 * Title : UserService.java
 * Package : indi.zqc.warehouse.service
 * Description : 用户服务接口
 * Create on : 2018/1/22 10:10
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public interface UserService {

    int insertUser(User user);

    int deleteUser(String userCode);

    int updateUser(User user);

    User selectUser(String userCode);

    Page<User> selectUserPage(UserCondition condition);
}

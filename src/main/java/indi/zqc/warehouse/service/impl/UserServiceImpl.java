package indi.zqc.warehouse.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import indi.zqc.warehouse.dao.UserDao;
import indi.zqc.warehouse.model.User;
import indi.zqc.warehouse.model.condition.UserCondition;
import indi.zqc.warehouse.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Title : UserServiceImpl.java
 * Package : indi.zqc.warehouse.service.impl
 * Description : 用户服务实现
 * Create on : 2018/1/22 10:13
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public int insertUser(User user) {
        return userDao.insertUser(user);
    }

    @Override
    public int deleteUser(String userCode) {
        return userDao.deleteUser(userCode);
    }

    @Override
    public int batchDeleteUser(String userCodes) {
        int deleteNum = 0;
        for (String userCode : userCodes.split(",")) {
            deleteNum += deleteUser(userCode);
        }
        return deleteNum;
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public User selectUser(String userCode) {
        return userDao.selectUser(userCode);
    }

    @Override
    public Page<User> selectUserPage(UserCondition condition) {
        PageHelper.startPage(condition.getPageNum(), condition.getNumPerPage());
        return userDao.selectUserPage(condition);
    }
}

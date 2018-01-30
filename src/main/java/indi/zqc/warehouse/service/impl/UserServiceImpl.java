package indi.zqc.warehouse.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import indi.zqc.warehouse.constant.Constants;
import indi.zqc.warehouse.dao.UserDao;
import indi.zqc.warehouse.dao.UserRoleDao;
import indi.zqc.warehouse.exception.BusinessException;
import indi.zqc.warehouse.model.User;
import indi.zqc.warehouse.model.condition.UserCondition;
import indi.zqc.warehouse.service.UserService;
import indi.zqc.warehouse.util.EncryptAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Title : UserServiceImpl.java
 * Package : indi.zqc.warehouse.service.impl
 * Description : 用户服务实现
 * Create on : 2018/1/22 10:13
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public int insertUser(User user) throws Exception {
        user.setPassword(EncryptAlgorithm.hexMD5(Constants.DEFAULT_PASSWORD));
        return userDao.insertUser(user);
    }

    @Override
    public int deleteUser(String userCode) {
        int del = userDao.deleteUser(userCode);
        userRoleDao.deleteUserRoleByUser(userCode);
        return del;
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

    @Override
    public int resetUser(String userCode) throws Exception {
        User user = selectUser(userCode);
        if (user == null) {
            throw new BusinessException("用户不存在");
        }
        user.setPassword(EncryptAlgorithm.hexMD5(Constants.DEFAULT_PASSWORD));
        return userDao.resetPassword(user);
    }

    @Override
    public int updateUserRole(String userCode, String[] roleCodes) {
        userRoleDao.deleteUserRoleByUser(userCode);
        int num = 0;
        for (String roleCode : roleCodes) {
            num += userRoleDao.insertUserRole(userCode, roleCode);
        }
        return num;
    }
}

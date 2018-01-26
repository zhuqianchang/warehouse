package indi.zqc.warehouse.shiro;

import indi.zqc.warehouse.constant.Errors;
import indi.zqc.warehouse.exception.BusinessException;
import indi.zqc.warehouse.model.User;
import indi.zqc.warehouse.service.UserService;
import indi.zqc.warehouse.util.EncryptAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Title : UserAuthenticationServiceImpl.java
 * Package : indi.zqc.warehouse.service.impl
 * Description : 用户权限认证实现
 * Create on : 2018/1/25 16:17
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    private static Logger logger = LoggerFactory.getLogger(UserAuthenticationServiceImpl.class);

    @Autowired
    private UserService userService;

    @Override
    public User doAuthentication(String userCode, String password) {
        User user = userService.selectUser(userCode);
        authentication(user, password);
        return user;
    }

    private void authentication(User user, String password) {
        if (user == null) {
            //用户不存在
            throw new BusinessException(Errors.Authentication.USER_NOT_EXISTS);
        }
        String md5Password = null;
        try {
            //密码加密
            md5Password = EncryptAlgorithm.hexMD5(password);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        if (!StringUtils.equals(user.getPassword(), md5Password)) {
            //密码错误
            throw new BusinessException(Errors.Authentication.PASSWORD_ERROR);
        }
    }
}

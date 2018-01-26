package indi.zqc.warehouse.shiro;

import indi.zqc.warehouse.model.User;

/**
 * Title : UserAuthenticationService.java
 * Package : indi.zqc.warehouse.service
 * Description : 用户权限认证接口
 * Create on : 2018/1/25 16:13
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public interface UserAuthenticationService {

    /**
     * 用户认证
     *
     * @param userCode
     * @param password
     * @return
     */
    User doAuthentication(String userCode, String password);

}

package indi.zqc.warehouse.shiro;

import indi.zqc.warehouse.model.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * Title : AuthRealm.java
 * Package : indi.zqc.warehouse.shiro
 * Description : 认证
 * Create on : 2018/1/25 16:01
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class AuthRealm extends AuthorizingRealm {

    private UserAuthenticationService userAuthenticationService;

    public AuthRealm(UserAuthenticationService userAuthenticationService) {
        this.userAuthenticationService = userAuthenticationService;
    }

    /**
     * 认证 登录
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        if (token instanceof UsernamePasswordToken) {
            UsernamePasswordToken uToken = (UsernamePasswordToken) token;
            String userCode = uToken.getUsername();
            String password = String.copyValueOf(uToken.getPassword());
            User user = userAuthenticationService.doAuthentication(userCode, password);
            SessionUser sessionUser = new SessionUser();
            sessionUser.setUserCode(user.getUserCode());
            sessionUser.setUserText(user.getUserText());
            return new SimpleAuthenticationInfo(sessionUser, password, getName());
        }
        return null;
    }

    /**
     * 授权<shiro:hasPermission>标签使用
     *
     * @param principal
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        return new SimpleAuthorizationInfo();
    }
}

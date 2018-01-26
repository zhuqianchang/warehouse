package indi.zqc.warehouse.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Title : AuthUtils.java
 * Package : indi.zqc.warehouse.shiro
 * Description : 权限工具
 * Create on : 2018/1/26 10:52
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class AuthUtils {

    private static final Logger logger = LoggerFactory.getLogger(AuthUtils.class);

    private AuthUtils() {
    }

    public static Object login(String userId, String password) throws Exception {
        UsernamePasswordToken loginToken = new UsernamePasswordToken(userId, password);
        Subject subject = SecurityUtils.getSubject();
        subject.login(loginToken);
        Object principle = subject.getPrincipal();
        setSessionUser(principle);
        return principle;
    }

    public static Object login(AuthenticationToken loginToken) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        subject.login(loginToken);
        Object principle = subject.getPrincipal();
        setSessionUser(principle);
        return principle;
    }

    public static Object getSessionUser() {
        return getSessionAttribute("SESSION-USER");
    }

    public static Object getSessionAttribute(String name) {
        Session session = SecurityUtils.getSubject().getSession(false);
        return session != null ? session.getAttribute(name) : null;
    }

    public static void setSessionUser(Object userPrinciple) {
        SecurityUtils.getSubject().getSession(true).setAttribute("SESSION-USER", userPrinciple);
    }

    public static void logout() {
        SecurityUtils.getSubject().logout();
    }

    public static String getSessionId() {
        Session session = SecurityUtils.getSubject().getSession(false);
        return session != null ? (session.getId() == null ? null : session.getId().toString()) : null;
    }
}

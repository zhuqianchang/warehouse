package indi.zqc.warehouse.util;

import indi.zqc.warehouse.shiro.AuthUtils;
import indi.zqc.warehouse.shiro.SessionUser;

/**
 * Title : SecurityContextUtils.java
 * Package : indi.zqc.warehouse.util
 * Description : 权限工具
 * Create on : 2018/1/24 18:06
 *
 * @author zhu.qianchang
 * @version v1.0.0
 */
public class SecurityContextUtils {

    public static String getCurrentUserCode() {
        SessionUser sessionUser = getCurrentUser();
        if (sessionUser != null) {
            return sessionUser.getUserCode();
        }
        return null;
    }

    public static SessionUser getCurrentUser() {
        Object sessionUser = AuthUtils.getSessionUser();
        if (sessionUser instanceof SessionUser) {
            return (SessionUser) sessionUser;
        }
        return null;
    }
}

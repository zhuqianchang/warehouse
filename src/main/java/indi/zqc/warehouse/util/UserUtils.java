package indi.zqc.warehouse.util;

import indi.zqc.warehouse.model.User;
import indi.zqc.warehouse.service.UserService;

/**
 * Title : UserUtils.java
 * Package : indi.zqc.warehouse.util
 * Description : 用户工具
 * Create on : 2018/2/2 19:29
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class UserUtils {

    private static UserService userService;

    public UserUtils(UserService userService) {
        UserUtils.userService = userService;
    }

    public static String selectUserText(String userCode) {
        User user = userService.selectUser(userCode);
        if (user != null) {
            return user.getUserText();
        }
        return null;
    }

}

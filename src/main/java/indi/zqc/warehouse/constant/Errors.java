package indi.zqc.warehouse.constant;

/**
 * Title : Error.java
 * Package : indi.zqc.warehouse.enums
 * Description : 错误
 * Create on : 2018/1/25 16:27
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public interface Errors {

    interface Authentication {
        String USER_NOT_EXISTS = "用户不存在";
        String PASSWORD_ERROR = "密码错误";
    }

}

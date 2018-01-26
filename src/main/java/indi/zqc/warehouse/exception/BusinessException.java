package indi.zqc.warehouse.exception;

/**
 * Title : BusinessException.java
 * Package : indi.zqc.warehouse.exception
 * Description : 业务异常类
 * Create on : 2018/1/25 16:19
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}

package indi.zqc.warehouse.util;

/**
 * Title : DatabaseUtils.java
 * Package : indi.zqc.warehouse.util
 * Description : 数据库工具
 * Create on : 2018/2/3 7:55
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class DatabaseUtils {

    private DatabaseUtils() {
    }

    /**
     * 是否外键约束
     * @param errorMsg
     * @return
     */
    public static boolean isConstraintsError(String errorMsg) {
        if (errorMsg.contains("ORA-02292") || errorMsg.contains("违反完整约束条件 ")
                || errorMsg.contains("DataIntegrityViolationException")
                || errorMsg.contains("Cannot delete or update a parent row")) {
            return true;
        }
        return false;
    }

    /**
     * 是否主键约束
     * @param errorMsg
     * @return
     */
    public static boolean isExistingPKError(String errorMsg) {
        if (errorMsg.contains("ORA-00001") || errorMsg.contains("违反唯一约束条件 ") || errorMsg.contains("DuplicateKeyException")
                || errorMsg.contains("Duplicate entry")) {
            return true;
        }
        return false;
    }
}

package indi.zqc.warehouse.constant;

/**
 * Title : SystemConstant.java
 * Package : indi.zqc.warehouse.constant
 * Description : 系统常量
 * Create on : 2018/1/26 16:11
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class Constants {

    //基础路径
    public static final String BASE_URL = "warehouse";

    //管理员
    public static final String ADMIN = "ADMIN";

    //初始密码
    public static final String DEFAULT_PASSWORD = "a1234567";

    //分隔符
    public static final String SEPARATOR = ",";

    //单据前缀
    public static final String DJ_PREFIX = "DJ";

    //采购清单前缀
    public static final String QD_PREFIX = "QD";

    //生产前缀
    public static final String SC_PREFIX = "SC";

    //成品类型
    public static final String PRODUCTION_TYPE = "03";

    //采购清单模板
    public static final String TEMPLATE_PURCHASE = "/static/template/purchase.xlsx";

    //库存模板
    public static final String TEMPLATE_STOCK = "/static/template/stock.xlsx";

    //出入库模板
    public static final String TEMPLATE_OPERATION_STOCK = "/static/template/operation_stock.xlsx";

    //生产表模板
    public static final String TEMPLATE_PRODUCE = "/static/template/produce.xlsx";

    //加密KEY
    public static final String KEY = "@ZZP@";
}

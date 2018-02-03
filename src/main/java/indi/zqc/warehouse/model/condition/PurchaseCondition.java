package indi.zqc.warehouse.model.condition;

import org.apache.commons.lang3.StringUtils;

/**
 * Title : PurchaseCondition.java
 * Package : indi.zqc.warehouse.model.condition
 * Description : 采购清单查询条件
 * Create on : 2018/2/1 17:41
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class PurchaseCondition extends Condition {

    //采购编号
    private String purchaseCode;

    //采购类型
    private String purchaseType;

    //状态
    private String purchaseStatus;

    public String getPurchaseCode() {
        return StringUtils.upperCase(purchaseCode);
    }

    public void setPurchaseCode(String purchaseCode) {
        this.purchaseCode = StringUtils.upperCase(purchaseCode);
    }

    public String getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
    }

    public String getPurchaseStatus() {
        return purchaseStatus;
    }

    public void setPurchaseStatus(String purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
    }
}

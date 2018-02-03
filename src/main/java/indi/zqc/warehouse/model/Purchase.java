package indi.zqc.warehouse.model;

import indi.zqc.warehouse.enums.PurchaseStatus;
import indi.zqc.warehouse.enums.PurchaseType;
import org.apache.commons.lang3.StringUtils;

/**
 * Title : Purchase.java
 * Package : indi.zqc.warehouse.model
 * Description : 采购清单
 * Create on : 2018/1/27 23:45
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class Purchase extends Common {

    //采购编号
    private String purchaseCode;

    //采购类型
    private String purchaseType;

    //采购类型描述
    private String purchaseTypeText;

    //采购状态
    private String purchaseStatus;

    //采购状态描述
    private String purchaseStatusText;

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

    public String getPurchaseTypeText() {
        if (StringUtils.isBlank(purchaseTypeText)) {
            purchaseTypeText = PurchaseType.getValue(purchaseType);
        }
        return purchaseTypeText;
    }

    public void setPurchaseTypeText(String purchaseTypeText) {
        this.purchaseTypeText = purchaseTypeText;
    }

    public String getPurchaseStatus() {
        return purchaseStatus;
    }

    public void setPurchaseStatus(String purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
    }

    public String getPurchaseStatusText() {
        if (StringUtils.isBlank(purchaseStatusText)) {
            purchaseStatusText = PurchaseStatus.getValue(purchaseStatus);
        }
        return purchaseStatusText;
    }

    public void setPurchaseStatusText(String purchaseStatusText) {
        this.purchaseStatusText = purchaseStatusText;
    }
}

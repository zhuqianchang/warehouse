package indi.zqc.warehouse.model;

/**
 * Title : Purchase.java
 * Package : indi.zqc.warehouse.model
 * Description : 采购
 * Create on : 2018/1/27 23:45
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class Purchase extends Common{

    //采购编号
    private String purchaseCode;

    //采购类型
    private String purchaseType;

    //状态
    private String status;

    public String getPurchaseCode() {
        return purchaseCode;
    }

    public void setPurchaseCode(String purchaseCode) {
        this.purchaseCode = purchaseCode;
    }

    public String getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

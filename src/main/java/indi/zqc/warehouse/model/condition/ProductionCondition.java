package indi.zqc.warehouse.model.condition;

/**
 * Title : ProductionCondition.java
 * Package : indi.zqc.warehouse.model.condition
 * Description : 成品查询条件
 * Create on : 2018/2/1 20:39
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class ProductionCondition extends Condition {

    //成品编号
    private String productionCode;

    //成品描述
    private String productionText;

    public String getProductionCode() {
        return productionCode;
    }

    public void setProductionCode(String productionCode) {
        this.productionCode = productionCode;
    }

    public String getProductionText() {
        return productionText;
    }

    public void setProductionText(String productionText) {
        this.productionText = productionText;
    }
}

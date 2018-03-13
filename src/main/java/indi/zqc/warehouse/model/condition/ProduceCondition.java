package indi.zqc.warehouse.model.condition;

/**
 * Title : ProduceCondition.java
 * Package : indi.zqc.warehouse.model.condition
 * Description : 生产查询条件
 * Create on : 2018/3/12 20:37
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class ProduceCondition extends Condition {

    //生产编号
    private String produceCode;

    //成品编号
    private String productionCode;

    //成品描述
    private String productionText;

    //生产日期开始
    private String produceDateStart;

    //生产日期结束
    private String produceDateEnd;

    public String getProduceCode() {
        return produceCode;
    }

    public void setProduceCode(String produceCode) {
        this.produceCode = produceCode;
    }

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

    public String getProduceDateStart() {
        return produceDateStart;
    }

    public void setProduceDateStart(String produceDateStart) {
        this.produceDateStart = produceDateStart;
    }

    public String getProduceDateEnd() {
        return produceDateEnd;
    }

    public void setProduceDateEnd(String produceDateEnd) {
        this.produceDateEnd = produceDateEnd;
    }
}

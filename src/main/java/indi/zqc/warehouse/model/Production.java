package indi.zqc.warehouse.model;

/**
 * Title : Production.java
 * Package : indi.zqc.warehouse.model
 * Description : 成品
 * Create on : 2018/1/27 23:25
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class Production extends Common {

    //成品编号
    private String productionCode;

    //成品描述
    private String productionText;

    //物料编号
    private String materialCode;

    //数量
    private Integer quantity;

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

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

package indi.zqc.warehouse.model;

/**
 * Title : ProductionMaterial.java
 * Package : indi.zqc.warehouse.model
 * Description : 成品物料
 * Create on : 2018/2/1 20:28
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class ProductionMaterial {

    //成品编号
    private String productionCode;

    //物料编号
    private String materialCode;

    //物料描述
    private String materialText;

    //数量
    private Integer quantity;

    public String getProductionCode() {
        return productionCode;
    }

    public void setProductionCode(String productionCode) {
        this.productionCode = productionCode;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialText() {
        return materialText;
    }

    public void setMaterialText(String materialText) {
        this.materialText = materialText;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

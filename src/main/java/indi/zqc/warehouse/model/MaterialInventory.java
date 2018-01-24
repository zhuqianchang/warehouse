package indi.zqc.warehouse.model;

/**
 * Title : MaterialInventory.java
 * Package : indi.zqc.warehouse.model
 * Description : 物料库存
 * Create on : 2018/1/23 15:13
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class MaterialInventory extends Inventory {

    //物料编号
    private String materialCode;

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }
}

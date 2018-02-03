package indi.zqc.warehouse.model.condition;

import org.apache.commons.lang3.StringUtils;

/**
 * Title : StockCondition.java
 * Package : indi.zqc.warehouse.model.condition
 * Description : 库存查询条件
 * Create on : 2018/1/28 14:00
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class StockCondition extends Condition {

    //物料编号
    private String materialCode;

    //物料描述
    private String materialText;

    //物料类型
    private String materialType;

    //仓库编号
    private String warehouseCode;

    //仓库描述
    private String warehouseText;

    public String getMaterialCode() {
        return StringUtils.upperCase(materialCode);
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = StringUtils.upperCase(materialCode);
    }

    public String getMaterialText() {
        return materialText;
    }

    public void setMaterialText(String materialText) {
        this.materialText = materialText;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getWarehouseCode() {
        return StringUtils.upperCase(warehouseCode);
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = StringUtils.upperCase(warehouseCode);
    }

    public String getWarehouseText() {
        return warehouseText;
    }

    public void setWarehouseText(String warehouseText) {
        this.warehouseText = warehouseText;
    }
}

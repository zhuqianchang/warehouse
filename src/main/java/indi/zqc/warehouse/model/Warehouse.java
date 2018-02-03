package indi.zqc.warehouse.model;

import org.apache.commons.lang3.StringUtils;

/**
 * Title : Warehouse.java
 * Package : indi.zqc.warehouse.model
 * Description : 仓库
 * Create on : 2018/1/23 14:54
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class Warehouse extends Common {

    //仓库编号
    private String warehouseCode;

    //仓库名称
    private String warehouseText;

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

package indi.zqc.warehouse.model;

/**
 * Title : StockShift.java
 * Package : indi.zqc.warehouse.model
 * Description : 移库
 * Create on : 2018/3/21 9:35
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class StockShift {

    //原始仓库
    private String srcWarehouseCode;

    //移动物料
    private String materialCode;

    //目标仓库
    private String targetWarehouseCode;

    //移动数量
    private Integer shiftNum;

    public String getSrcWarehouseCode() {
        return srcWarehouseCode;
    }

    public void setSrcWarehouseCode(String srcWarehouseCode) {
        this.srcWarehouseCode = srcWarehouseCode;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getTargetWarehouseCode() {
        return targetWarehouseCode;
    }

    public void setTargetWarehouseCode(String targetWarehouseCode) {
        this.targetWarehouseCode = targetWarehouseCode;
    }

    public Integer getShiftNum() {
        return shiftNum;
    }

    public void setShiftNum(Integer shiftNum) {
        this.shiftNum = shiftNum;
    }
}

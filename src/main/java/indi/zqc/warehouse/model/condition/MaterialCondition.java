package indi.zqc.warehouse.model.condition;

import java.util.List;

/**
 * Title : MaterialCondition.java
 * Package : indi.zqc.warehouse.model.condition
 * Description : 物料查询条件
 * Create on : 2018/1/28 13:41
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class MaterialCondition extends Condition {

    //物料编号
    private String materialCode;

    //物料描述
    private String materialText;

    //物料类型
    private String materialType;

    //物料类型
    private List<String> materialTypes;

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

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public List<String> getMaterialTypes() {
        return materialTypes;
    }

    public void setMaterialTypes(List<String> materialTypes) {
        this.materialTypes = materialTypes;
    }
}

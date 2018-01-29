package indi.zqc.warehouse.model;

import indi.zqc.warehouse.enums.ECodeType;
import indi.zqc.warehouse.util.ECodeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Title : Material.java
 * Package : indi.zqc.warehouse.model
 * Description : 物料
 * Create on : 2018/1/23 14:49
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class Material extends Common{

    //物料编号
    private String materialCode;

    //物料名称
    private String materialText;

    //物料类型
    private String materialType;

    //物料类型描述
    private String materialTypeText;

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

    public String getMaterialTypeText() {
        if (StringUtils.isBlank(materialTypeText)) {
            materialTypeText = ECodeUtils.selectECodeText(ECodeType.MATERIAL.getKey(), this.getMaterialType());
        }
        return materialTypeText;
    }

    public void setMaterialTypeText(String materialTypeText) {
        this.materialTypeText = materialTypeText;
    }
}

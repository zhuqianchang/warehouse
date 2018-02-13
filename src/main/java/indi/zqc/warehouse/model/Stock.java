package indi.zqc.warehouse.model;

import indi.zqc.warehouse.enums.ECodeType;
import indi.zqc.warehouse.util.ECodeUtils;
import indi.zqc.warehouse.util.UserUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * Title : Stock.java
 * Package : indi.zqc.warehouse.model
 * Description : 库存
 * Create on : 2018/1/27 23:29
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class Stock extends Common {

    //物料编号
    private String materialCode;

    //物料描述
    private String materialText;

    //物料类型
    private String materialType;

    //物料类型描述
    private String materialTypeText;

    //仓库编号
    private String warehouseCode;

    //仓库描述
    private String warehouseText;

    //库存
    private Integer stock;

    //编辑人
    private String editor;

    //编辑人描述
    private String editorText;

    //编辑时间
    private Date editTime;

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

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getWarehouseText() {
        return warehouseText;
    }

    public void setWarehouseText(String warehouseText) {
        this.warehouseText = warehouseText;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getEditorText() {
        if (StringUtils.isBlank(editorText)) {
            editorText = UserUtils.selectUserText(editor);
        }
        return editorText;
    }

    public void setEditorText(String editorText) {
        this.editorText = editorText;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }
}

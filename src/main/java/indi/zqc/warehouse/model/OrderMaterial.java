package indi.zqc.warehouse.model;

/**
 * Title : OrderMaterial.java
 * Package : indi.zqc.warehouse.model
 * Description : 订单物料
 * Create on : 2018/2/2 16:59
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class OrderMaterial {

    //订单编号
    private String orderCode;

    //物料编号
    private String materialCode;

    //物料描述
    private String materialText;

    //数量
    private Integer quantity;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
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

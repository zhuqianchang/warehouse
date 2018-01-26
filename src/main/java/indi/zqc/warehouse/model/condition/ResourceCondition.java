package indi.zqc.warehouse.model.condition;

/**
 * Title : ResourceCondition.java
 * Package : indi.zqc.warehouse.model.condition
 * Description : 资源查询条件
 * Create on : 2018/1/26 15:03
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class ResourceCondition extends Condition{

    //资源编号
    private String resourceCode;

    //资源描述
    private String resourceText;

    //资源类型
    private String resourceTypeCode;

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public String getResourceText() {
        return resourceText;
    }

    public void setResourceText(String resourceText) {
        this.resourceText = resourceText;
    }

    public String getResourceTypeCode() {
        return resourceTypeCode;
    }

    public void setResourceTypeCode(String resourceTypeCode) {
        this.resourceTypeCode = resourceTypeCode;
    }
}

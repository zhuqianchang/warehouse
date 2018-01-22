package indi.zqc.warehouse.model;

import java.util.Date;

/**
 * Title : Common.java
 * Package : indi.zqc.warehouse.model
 * Description : 实体基础类
 * Create on : 2018/1/22 10:05
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public abstract class Common {

    //创建人
    private String createUser;

    //创建时间
    private Date createDateTime;

    //修改人
    private String modifyUser;

    //修改时间
    private Date modifyDateTime;

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public Date getModifyDateTime() {
        return modifyDateTime;
    }

    public void setModifyDateTime(Date modifyDateTime) {
        this.modifyDateTime = modifyDateTime;
    }
}

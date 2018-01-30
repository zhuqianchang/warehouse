package indi.zqc.warehouse.model.condition;

import java.util.Date;
import java.util.List;

/**
 * Title : Condition.java
 * Package : indi.zqc.warehouse.model.condition
 * Description : 查询条件基础类
 * Create on : 2018/1/22 10:08
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public abstract class Condition {

    //创建人
    private String createUser;

    //开始创建时间
    private Date minCreateDateTime;

    //结束创建时间
    private Date maxCreateDateTime;

    //修改人
    private String modifyUser;

    //开始修改时间
    private Date minModifyDateTime;

    //结束修改时间
    private Date maxModifyDateTime;

    //每页显示条数
    private int numPerPage = 20;

    //当前页号
    private int pageNum = 1;

    //排序字段
    private String orderField = "modifyDateTime";

    //排序方式 默认降序
    private String orderDirection = "desc";

    //总条数
    private long totalCount;

    //返回结果集
    private List data;

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getMinCreateDateTime() {
        return minCreateDateTime;
    }

    public void setMinCreateDateTime(Date minCreateDateTime) {
        this.minCreateDateTime = minCreateDateTime;
    }

    public Date getMaxCreateDateTime() {
        return maxCreateDateTime;
    }

    public void setMaxCreateDateTime(Date maxCreateDateTime) {
        this.maxCreateDateTime = maxCreateDateTime;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public Date getMinModifyDateTime() {
        return minModifyDateTime;
    }

    public void setMinModifyDateTime(Date minModifyDateTime) {
        this.minModifyDateTime = minModifyDateTime;
    }

    public Date getMaxModifyDateTime() {
        return maxModifyDateTime;
    }

    public void setMaxModifyDateTime(Date maxModifyDateTime) {
        this.maxModifyDateTime = maxModifyDateTime;
    }

    public int getNumPerPage() {
        return numPerPage;
    }

    public void setNumPerPage(int numPerPage) {
        this.numPerPage = numPerPage;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public String getOrderDirection() {
        return orderDirection;
    }

    public void setOrderDirection(String orderDirection) {
        this.orderDirection = orderDirection;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}

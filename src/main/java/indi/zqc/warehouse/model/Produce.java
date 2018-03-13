package indi.zqc.warehouse.model;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Date;

/**
 * Title : Produce.java
 * Package : indi.zqc.warehouse.model
 * Description : 生产
 * Create on : 2018/3/12 20:28
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class Produce extends Common {

    //生产编号
    private String produceCode;

    //成品编号
    private String productionCode;

    //成品描述
    private String productionText;

    //数量
    private Integer quantity;

    //生产日期
    private String produceDateStr;

    //生产日期
    private Date produceDate;

    public String getProduceCode() {
        return produceCode;
    }

    public void setProduceCode(String produceCode) {
        this.produceCode = produceCode;
    }

    public String getProductionCode() {
        return productionCode;
    }

    public void setProductionCode(String productionCode) {
        this.productionCode = productionCode;
    }

    public String getProductionText() {
        return productionText;
    }

    public void setProductionText(String productionText) {
        this.productionText = productionText;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getProduceDateStr() {
        if (StringUtils.isBlank(produceDateStr) && produceDate != null) {
            produceDateStr = DateFormatUtils.format(produceDate, "yyyy-MM-dd");
        }
        return produceDateStr;
    }

    public void setProduceDateStr(String produceDateStr) {
        this.produceDateStr = produceDateStr;
    }

    public Date getProduceDate() throws ParseException {
        if (produceDate == null && StringUtils.isNotBlank(produceDateStr)) {
            produceDate = DateUtils.parseDate(produceDateStr, "yyyy-MM-dd");
        }
        return produceDate;
    }

    public void setProduceDate(Date produceDate) {
        this.produceDate = produceDate;
    }
}

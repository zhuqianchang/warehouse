package indi.zqc.warehouse.model;

/**
 * Title : ECode.java
 * Package : indi.zqc.warehouse.model
 * Description : 枚举码
 * Create on : 2018/1/28 13:08
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class ECode {

    //枚举类型
    private String eCodeType;

    //枚举码
    private String eCode;

    //枚举码描述
    private String eCodeText;

    //显示序号
    private int displayOrdinal;

    public String geteCodeType() {
        return eCodeType;
    }

    public void seteCodeType(String eCodeType) {
        this.eCodeType = eCodeType;
    }

    public String geteCode() {
        return eCode;
    }

    public void seteCode(String eCode) {
        this.eCode = eCode;
    }

    public String geteCodeText() {
        return eCodeText;
    }

    public void seteCodeText(String eCodeText) {
        this.eCodeText = eCodeText;
    }

    public int getDisplayOrdinal() {
        return displayOrdinal;
    }

    public void setDisplayOrdinal(int displayOrdinal) {
        this.displayOrdinal = displayOrdinal;
    }
}

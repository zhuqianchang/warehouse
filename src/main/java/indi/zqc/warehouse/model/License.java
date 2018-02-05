package indi.zqc.warehouse.model;

import java.util.Date;

/**
 * Title : License.java
 * Package : indi.zqc.warehouse.model
 * Description : License
 * Create on : 2018/2/5 10:57
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class License {

    private String license;

    private String macAddress;

    private String macAddressText;

    private Date limitDate;

    public License() {
    }

    public License(String license) {
        this.license = license;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getMacAddressText() {
        return macAddressText;
    }

    public void setMacAddressText(String macAddressText) {
        this.macAddressText = macAddressText;
    }

    public Date getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(Date limitDate) {
        this.limitDate = limitDate;
    }
}

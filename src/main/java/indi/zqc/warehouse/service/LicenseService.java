package indi.zqc.warehouse.service;

import indi.zqc.warehouse.model.License;

/**
 * Title : LicenseService.java
 * Package : indi.zqc.warehouse.service
 * Description : License服务
 * Create on : 2018/2/5 11:06
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public interface LicenseService {

    License selectLicense();

    int saveLicense(String limitDate, String key);

    void validateLicense() throws Exception;
}

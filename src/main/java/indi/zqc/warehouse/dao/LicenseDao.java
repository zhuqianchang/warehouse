package indi.zqc.warehouse.dao;

import indi.zqc.warehouse.model.License;

/**
 * Title : LicenseDao.java
 * Package : indi.zqc.warehouse.dao
 * Description : LicenseDao
 * Create on : 2018/2/5 10:59
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public interface LicenseDao {

    int insertLicense(License license);

    int deleteLicense();

    License selectLicense();
}

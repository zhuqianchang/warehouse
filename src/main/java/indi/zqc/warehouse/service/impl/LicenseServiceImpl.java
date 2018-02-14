package indi.zqc.warehouse.service.impl;

import indi.zqc.warehouse.constant.Constants;
import indi.zqc.warehouse.dao.LicenseDao;
import indi.zqc.warehouse.exception.BusinessException;
import indi.zqc.warehouse.model.License;
import indi.zqc.warehouse.service.LicenseService;
import indi.zqc.warehouse.util.MACUtils;
import indi.zqc.warehouse.util.encode.AES;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Title : LicenseServiceImpl.java
 * Package : indi.zqc.warehouse.service.impl
 * Description : License服务实现
 * Create on : 2018/2/5 11:08
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
@Transactional
@Service
public class LicenseServiceImpl implements LicenseService {

    private Logger logger = LoggerFactory.getLogger(LicenseServiceImpl.class);

    @Autowired
    private LicenseDao licenseDao;

    @Override
    public License selectLicense() {
        License license = licenseDao.selectLicense();
        if (license != null && StringUtils.isNotBlank(license.getLicense())) {
            try {
                String decrypt = AES.decrypt(license.getLicense(), Constants.KEY);
                String macAddress = decrypt.split(Constants.SEPARATOR)[0];
                String limitDateStr = decrypt.split(Constants.SEPARATOR)[1];
                license.setMacAddress(macAddress);
                license.setLimitDate(DateUtils.parseDate(limitDateStr, "yyyy-MM-dd"));
                String nowMacAddress = MACUtils.getMACAddress();
                if (StringUtils.isNotBlank(macAddress) && !StringUtils.equals(macAddress, nowMacAddress)) {
                    license.setMacAddressText("异常");
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        return license;
    }

    @Override
    public int saveLicense(String limitDate, String key) {
        if (!StringUtils.equals(Constants.KEY, key)) {
            throw new BusinessException("密码错误");
        }
        licenseDao.deleteLicense();
        String license = AES.encryptAES(MACUtils.getMACAddress() + Constants.SEPARATOR + limitDate, key);
        return licenseDao.insertLicense(new License(license));
    }

    @Override
    public void validateLicense() throws Exception {
        License license = licenseDao.selectLicense();
        try {
            String decrypt = AES.decrypt(license.getLicense(), Constants.KEY);
            String macAddress = decrypt.split(Constants.SEPARATOR)[0];
            String limitDateStr = decrypt.split(Constants.SEPARATOR)[1];
//            if (!StringUtils.equals(macAddress, MACUtils.getMACAddress())) {
//                throw new BusinessException("系统认证异常，MAC地址错误");
//            }
            Date limitDate = DateUtils.parseDate(limitDateStr, "yyyy-MM-dd");
            if (limitDate.before(new Date())) {
                throw new BusinessException("系统认证异常，已过期");
            }
        } catch (Exception e) {
            if (e instanceof BusinessException) {
                throw e;
            } else {
                throw new BusinessException("系统认证异常", e);
            }
        }
    }
}

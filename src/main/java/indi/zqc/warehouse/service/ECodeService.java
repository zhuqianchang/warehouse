package indi.zqc.warehouse.service;

import indi.zqc.warehouse.model.ECode;

import java.util.List;

/**
 * Title : ECodeService.java
 * Package : indi.zqc.warehouse.service
 * Description : 枚举码服务
 * Create on : 2018/1/28 13:13
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public interface ECodeService {

    List<ECode> selectECodeList(String eCodeType);

    ECode selectECode(String eCodeType, String eCode);
}

package indi.zqc.warehouse.util;

import indi.zqc.warehouse.model.ECode;
import indi.zqc.warehouse.service.ECodeService;

import java.util.List;

/**
 * Title : ECodeUtils.java
 * Package : indi.zqc.warehouse.util
 * Description : 枚举码工具
 * Create on : 2018/1/28 15:25
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class ECodeUtils {

    private static ECodeService eCodeService;

    public ECodeUtils(ECodeService eCodeService) {
        ECodeUtils.eCodeService = eCodeService;
    }

    public static List<ECode> selectECodeList(String eCodeType) {
        return eCodeService.selectECodeList(eCodeType);
    }

    public static ECode selectECode(String eCodeType, String eCode) {
        return eCodeService.selectECode(eCodeType, eCode);
    }

    public static String selectECodeText(String eCodeType, String eCode) {
        ECode selectECode = eCodeService.selectECode(eCodeType, eCode);
        if (selectECode != null) {
            return selectECode.geteCodeText();
        }
        return null;
    }
}

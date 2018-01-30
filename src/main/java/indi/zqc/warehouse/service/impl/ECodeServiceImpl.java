package indi.zqc.warehouse.service.impl;

import indi.zqc.warehouse.dao.ECodeDao;
import indi.zqc.warehouse.model.ECode;
import indi.zqc.warehouse.service.ECodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Title : ECodeServiceImpl.java
 * Package : indi.zqc.warehouse.service.impl
 * Description : 枚举码服务实现
 * Create on : 2018/1/28 13:16
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
@Transactional
@Service
public class ECodeServiceImpl implements ECodeService {

    @Autowired
    private ECodeDao eCodeDao;

    @Override
    public List<ECode> selectECodeList(String eCodeType) {
        return eCodeDao.selectECodeList(eCodeType);
    }

    @Override
    public ECode selectECode(String eCodeType, String eCode) {
        return eCodeDao.selectECode(eCodeType, eCode);
    }
}

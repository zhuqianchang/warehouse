package indi.zqc.warehouse.service.impl;

import indi.zqc.warehouse.dao.PurchaseProductionDao;
import indi.zqc.warehouse.model.PurchaseProduction;
import indi.zqc.warehouse.service.PurchaseProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Title : PurchaseProductionServiceImpl.java
 * Package : indi.zqc.warehouse.service.impl
 * Description : 采购成品服务实现
 * Create on : 2018/2/2 18:38
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
@Transactional
@Service
public class PurchaseProductionServiceImpl implements PurchaseProductionService {

    @Autowired
    private PurchaseProductionDao purchaseProductionDao;

    @Override
    public List<PurchaseProduction> selectPurchaseProduction(String purchaseCode) {
        return purchaseProductionDao.selectPurchaseProduction(purchaseCode);
    }
}

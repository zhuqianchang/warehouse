package indi.zqc.warehouse.service.impl;

import indi.zqc.warehouse.dao.PurchaseOrderDao;
import indi.zqc.warehouse.model.PurchaseOrder;
import indi.zqc.warehouse.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Title : PurchaseOrderServiceImpl.java
 * Package : indi.zqc.warehouse.service.impl
 * Description : 采购订单服务实现
 * Create on : 2018/2/2 18:38
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
@Transactional
@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    @Autowired
    private PurchaseOrderDao purchaseOrderDao;

    @Override
    public List<PurchaseOrder> selectPurchaseOrder(String purchaseCode) {
        return purchaseOrderDao.selectPurchaseOrder(purchaseCode);
    }
}

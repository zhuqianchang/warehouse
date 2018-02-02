package indi.zqc.warehouse.service;

import indi.zqc.warehouse.model.PurchaseProduction;

import java.util.List;

/**
 * Title : PurchaseProductionService.java
 * Package : indi.zqc.warehouse.service
 * Description : 采购成品服务
 * Create on : 2018/2/2 18:37
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public interface PurchaseProductionService {

    List<PurchaseProduction> selectPurchaseProduction(String purchaseCode);
}

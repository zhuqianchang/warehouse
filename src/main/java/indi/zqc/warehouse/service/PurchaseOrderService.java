package indi.zqc.warehouse.service;

import indi.zqc.warehouse.model.PurchaseOrder;

import java.util.List;

/**
 * Title : PurchaseOrderService.java
 * Package : indi.zqc.warehouse.service
 * Description : 采购订单服务
 * Create on : 2018/2/3 19:56
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public interface PurchaseOrderService {

    List<PurchaseOrder> selectPurchaseOrder(String purchaseCode);
}

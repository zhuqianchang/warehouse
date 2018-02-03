package indi.zqc.warehouse.dao;

import indi.zqc.warehouse.model.PurchaseOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Title : PurchaseOrderDao.java
 * Package : indi.zqc.warehouse.dao
 * Description : 采购订单
 * Create on : 2018/2/3 19:34
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public interface PurchaseOrderDao {

    int insertPurchaseOrder(PurchaseOrder purchaseOrder);

    int deletePurchaseOrder(@Param("purchaseCode") String purchaseCode);

    List<PurchaseOrder> selectPurchaseOrder(@Param("purchaseCode") String purchaseCode);
}

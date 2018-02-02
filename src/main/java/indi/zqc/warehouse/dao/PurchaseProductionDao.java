package indi.zqc.warehouse.dao;

import indi.zqc.warehouse.model.PurchaseProduction;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Title : PurchaseProductionDao.java
 * Package : indi.zqc.warehouse.dao
 * Description : 采购产品DAO
 * Create on : 2018/2/2 17:56
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public interface PurchaseProductionDao {

    int insertPurchaseProduction(PurchaseProduction purchaseProduction);

    int deletePurchaseProduction(@Param("purchaseCode") String purchaseCode);

    List<PurchaseProduction> selectPurchaseProduction(@Param("purchaseCode") String purchaseCode);
}

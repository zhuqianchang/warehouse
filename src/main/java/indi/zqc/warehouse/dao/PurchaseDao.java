package indi.zqc.warehouse.dao;

import com.github.pagehelper.Page;
import indi.zqc.warehouse.model.Purchase;
import indi.zqc.warehouse.model.condition.PurchaseCondition;
import org.apache.ibatis.annotations.Param;

/**
 * Title : PurchaseDao.java
 * Package : indi.zqc.purchase.dao
 * Description : 采购清单DAO
 * Create on : 2018/2/1 17:16
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public interface PurchaseDao {

    int insertPurchase(Purchase purchase);

    int deletePurchase(@Param("purchaseCode") String purchaseCode);

    int updatePurchase(Purchase purchase);

    Purchase selectPurchase(@Param("purchaseCode") String purchaseCode);

    Page<Purchase> selectPurchasePage(PurchaseCondition condition);
}

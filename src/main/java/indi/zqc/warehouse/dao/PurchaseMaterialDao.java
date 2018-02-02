package indi.zqc.warehouse.dao;

import indi.zqc.warehouse.model.PurchaseMaterial;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Title : PurchaseMaterialDao.java
 * Package : indi.zqc.warehouse.dao
 * Description : 库存物料
 * Create on : 2018/2/1 19:33
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public interface PurchaseMaterialDao {

    int insertPurchaseMaterial(PurchaseMaterial purchase);

    int deletePurchaseMaterial(@Param("purchaseCode") String purchaseCode);

    List<PurchaseMaterial> selectPurchaseMaterial(@Param("purchaseCode") String purchaseCode);
}

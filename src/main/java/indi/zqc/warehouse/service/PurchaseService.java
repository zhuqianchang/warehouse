package indi.zqc.warehouse.service;

import com.github.pagehelper.Page;
import indi.zqc.warehouse.model.Purchase;
import indi.zqc.warehouse.model.condition.PurchaseCondition;

/**
 * Title : PurchaseService.java
 * Package : indi.zqc.warehouse.service
 * Description : 采购清单服务
 * Create on : 2018/1/28 13:42
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public interface PurchaseService {

    int insertPurchase(Purchase purchase);

    int deletePurchase(String purchaseCode);

    int updatePurchase(Purchase purchase);

    Purchase selectPurchase(String purchaseCode);

    Page<Purchase> selectPurchasePage(PurchaseCondition condition);

    int savePurchase(Purchase purchase, String materials);

    String producePurchase(String orderCode, String userCode);
}

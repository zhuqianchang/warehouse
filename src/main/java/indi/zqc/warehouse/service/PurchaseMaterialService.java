package indi.zqc.warehouse.service;

import indi.zqc.warehouse.model.PurchaseMaterial;
import indi.zqc.warehouse.model.PurchaseProduction;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Title : PurchaseMaterialService.java
 * Package : indi.zqc.warehouse.service
 * Description : 采购物料服务接口
 * Create on : 2018/2/1 19:47
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public interface PurchaseMaterialService {

    List<PurchaseMaterial> selectPurchaseMaterial(String purchaseCode);
}

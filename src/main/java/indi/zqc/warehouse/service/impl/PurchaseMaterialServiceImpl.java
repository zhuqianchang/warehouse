package indi.zqc.warehouse.service.impl;

import indi.zqc.warehouse.dao.MaterialDao;
import indi.zqc.warehouse.dao.PurchaseDao;
import indi.zqc.warehouse.dao.PurchaseMaterialDao;
import indi.zqc.warehouse.dao.PurchaseOrderDao;
import indi.zqc.warehouse.enums.PurchaseType;
import indi.zqc.warehouse.exception.BusinessException;
import indi.zqc.warehouse.model.Purchase;
import indi.zqc.warehouse.model.PurchaseMaterial;
import indi.zqc.warehouse.model.PurchaseOrder;
import indi.zqc.warehouse.service.OrderProductionService;
import indi.zqc.warehouse.service.PurchaseMaterialService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Title : PurchaseMaterialServiceImpl.java
 * Package : indi.zqc.warehouse.service.impl
 * Description : 采购物料服务实现
 * Create on : 2018/2/1 19:47
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
@Transactional
@Service
public class PurchaseMaterialServiceImpl implements PurchaseMaterialService {

    @Autowired
    private PurchaseDao purchaseDao;

    @Autowired
    private PurchaseMaterialDao purchaseMaterialDao;

    @Autowired
    private PurchaseOrderDao purchaseOrderDaoDao;

    @Autowired
    private MaterialDao materialDao;

    @Autowired
    private OrderProductionService orderProductionService;

    @Override
    public List<PurchaseMaterial> selectPurchaseMaterial(String purchaseCode) {
        Purchase purchase = purchaseDao.selectPurchase(purchaseCode);
        if (purchase == null) {
            throw new BusinessException("采购清单不存在");
        }
        if (StringUtils.equals(purchase.getPurchaseType(), PurchaseType.AUTO.getKey())) {
            List<PurchaseMaterial> purchaseMaterials = new ArrayList<>();
            //订单
            List<PurchaseOrder> purchaseOrders = purchaseOrderDaoDao.selectPurchaseOrder(purchaseCode);
            List<String> orderCodes = new ArrayList<>();
            for (PurchaseOrder purchaseOrder : purchaseOrders) {
                orderCodes.add(purchaseOrder.getOrderCode());
            }
            Map<String, Integer> materialMap = orderProductionService.selectOrderMaterialMap(orderCodes.toArray(new String[orderCodes.size()]));
            Iterator<Map.Entry<String, Integer>> it = materialMap.entrySet().iterator();
            while (it.hasNext()) {
                PurchaseMaterial purchaseMaterial = new PurchaseMaterial();
                Map.Entry<String, Integer> entry = it.next();
                purchaseMaterial.setPurchaseCode(purchaseCode);
                purchaseMaterial.setMaterialCode(entry.getKey());
                purchaseMaterial.setMaterialText(materialDao.selectMaterial(entry.getKey()).getMaterialText());
                purchaseMaterial.setQuantity(entry.getValue());
                purchaseMaterials.add(purchaseMaterial);
            }
            return purchaseMaterials;
        }
        return purchaseMaterialDao.selectPurchaseMaterial(purchaseCode);
    }
}

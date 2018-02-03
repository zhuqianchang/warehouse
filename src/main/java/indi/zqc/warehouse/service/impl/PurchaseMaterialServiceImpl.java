package indi.zqc.warehouse.service.impl;

import indi.zqc.warehouse.dao.*;
import indi.zqc.warehouse.enums.PurchaseType;
import indi.zqc.warehouse.exception.BusinessException;
import indi.zqc.warehouse.model.ProductionMaterial;
import indi.zqc.warehouse.model.Purchase;
import indi.zqc.warehouse.model.PurchaseMaterial;
import indi.zqc.warehouse.model.PurchaseProduction;
import indi.zqc.warehouse.service.PurchaseMaterialService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    private PurchaseProductionDao purchaseProductionDao;

    @Autowired
    private ProductionMaterialDao productionMaterialDao;

    @Autowired
    private MaterialDao materialDao;

    @Override
    public List<PurchaseMaterial> selectPurchaseMaterial(String purchaseCode) {
        Purchase purchase = purchaseDao.selectPurchase(purchaseCode);
        if (purchase == null) {
            throw new BusinessException("采购清单不存在");
        }
        if (StringUtils.equals(purchase.getPurchaseType(), PurchaseType.AUTO.getKey())) {
            List<PurchaseMaterial> purchaseMaterials = new ArrayList<>();
            //订单中的成品
            List<PurchaseProduction> purchaseProductions = purchaseProductionDao.selectPurchaseProduction(purchaseCode);
            Map<String, Integer> materialMap = new HashMap<>();
            for (PurchaseProduction purchaseProduction : purchaseProductions) {
                //成品中的物料
                List<ProductionMaterial> productionMaterials = productionMaterialDao.selectProductionMaterial(purchaseProduction.getProductionCode());
                for (ProductionMaterial productionMaterial : productionMaterials) {
                    String materialCode = productionMaterial.getMaterialCode();
                    if (materialMap.containsKey(materialCode)) {
                        materialMap.put(materialCode, materialMap.get(materialCode) + productionMaterial.getQuantity() * purchaseProduction.getQuantity());
                    } else {
                        materialMap.put(materialCode, productionMaterial.getQuantity() * purchaseProduction.getQuantity());
                    }
                }
            }
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

package indi.zqc.warehouse.service.impl;

import indi.zqc.warehouse.dao.OrderProductionDao;
import indi.zqc.warehouse.dao.ProductionMaterialDao;
import indi.zqc.warehouse.model.OrderProduction;
import indi.zqc.warehouse.model.ProductionMaterial;
import indi.zqc.warehouse.service.OrderProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Title : OrderProductionServiceImpl.java
 * Package : indi.zqc.warehouse.service.impl
 * Description : 订单成品服务实现
 * Create on : 2018/2/2 15:35
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
@Transactional
@Service
public class OrderProductionServiceImpl implements OrderProductionService {

    @Autowired
    private OrderProductionDao orderProductionDao;

    @Autowired
    private ProductionMaterialDao productionMaterialDao;

    @Override
    public List<OrderProduction> selectOrderProduction(String orderCode) {
        return orderProductionDao.selectOrderProduction(orderCode);
    }

    @Override
    public Map<String, Integer> selectOrderMaterialMap(String orderCode) {
        Map<String, Integer> materialMap = new HashMap<>();
        List<OrderProduction> orderProductions = selectOrderProduction(orderCode);
        for (OrderProduction orderProduction : orderProductions) {
            //成品中的物料
            List<ProductionMaterial> productionMaterials = productionMaterialDao.selectProductionMaterial(orderProduction.getProductionCode());
            for (ProductionMaterial productionMaterial : productionMaterials) {
                String materialCode = productionMaterial.getMaterialCode();
                if (materialMap.containsKey(materialCode)) {
                    materialMap.put(materialCode, materialMap.get(materialCode) + productionMaterial.getQuantity() * productionMaterial.getQuantity());
                } else {
                    materialMap.put(materialCode, productionMaterial.getQuantity() * productionMaterial.getQuantity());
                }
            }
        }
        return materialMap;
    }

    @Override
    public Map<String, Integer> selectOrderMaterialMap(String[] orderCodes) {
        Map<String, Integer> materialMap = new HashMap<>();
        for (String orderCode : orderCodes) {
            List<OrderProduction> orderProductions = selectOrderProduction(orderCode);
            for (OrderProduction orderProduction : orderProductions) {
                //成品中的物料
                List<ProductionMaterial> productionMaterials = productionMaterialDao.selectProductionMaterial(orderProduction.getProductionCode());
                for (ProductionMaterial productionMaterial : productionMaterials) {
                    String materialCode = productionMaterial.getMaterialCode();
                    if (materialMap.containsKey(materialCode)) {
                        materialMap.put(materialCode, materialMap.get(materialCode) + productionMaterial.getQuantity() * productionMaterial.getQuantity());
                    } else {
                        materialMap.put(materialCode, productionMaterial.getQuantity() * productionMaterial.getQuantity());
                    }
                }
            }
        }
        return materialMap;
    }
}

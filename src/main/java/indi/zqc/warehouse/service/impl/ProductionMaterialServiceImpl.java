package indi.zqc.warehouse.service.impl;

import indi.zqc.warehouse.dao.ProductionMaterialDao;
import indi.zqc.warehouse.model.ProductionMaterial;
import indi.zqc.warehouse.service.ProductionMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Title : ProductionMaterialServiceImpl.java
 * Package : indi.zqc.warehouse.service.impl
 * Description : 成品物料服务实现
 * Create on : 2018/2/1 20:45
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
@Transactional
@Service
public class ProductionMaterialServiceImpl implements ProductionMaterialService {

    @Autowired
    private ProductionMaterialDao productionMaterialDao;

    @Override
    public List<ProductionMaterial> selectProductionMaterial(String productionCode) {
        return productionMaterialDao.selectProductionMaterial(productionCode);
    }
}

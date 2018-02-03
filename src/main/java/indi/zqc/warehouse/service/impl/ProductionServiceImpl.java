package indi.zqc.warehouse.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import indi.zqc.warehouse.dao.ProductionDao;
import indi.zqc.warehouse.dao.ProductionMaterialDao;
import indi.zqc.warehouse.model.Production;
import indi.zqc.warehouse.model.ProductionMaterial;
import indi.zqc.warehouse.model.condition.ProductionCondition;
import indi.zqc.warehouse.service.ProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Title : ProductionServiceImpl.java
 * Package : indi.zqc.warehouse.service.impl
 * Description : 成品服务实现
 * Create on : 2018/2/1 20:40
 * 
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
@Transactional
@Service
public class ProductionServiceImpl implements ProductionService {

    @Autowired
    private ProductionDao productionDao;

    @Autowired
    private ProductionMaterialDao productionMaterialDao;

    @Override
    public int insertProduction(Production production) {
        return productionDao.insertProduction(production);
    }

    @Override
    public int deleteProduction(String productionCode) {
        productionMaterialDao.deleteProductionMaterial(productionCode);
        return productionDao.deleteProduction(productionCode);
    }

    @Override
    public int updateProduction(Production production) {
        return productionDao.updateProduction(production);
    }

    @Override
    public Production selectProduction(String productionCode) {
        return productionDao.selectProduction(productionCode);
    }

    @Override
    public Page<Production> selectProductionPage(ProductionCondition condition) {
        PageHelper.startPage(condition.getPageNum(), condition.getNumPerPage());
        return productionDao.selectProductionPage(condition);
    }

    @Override
    public int saveProduction(Production production, String materials) {
        Production oldProduction = selectProduction(production.getProductionCode());
        int num;
        if (oldProduction == null) {
            num = insertProduction(production);
        } else {
            num = updateProduction(production);
        }
        productionMaterialDao.deleteProductionMaterial(production.getProductionCode());
        List<ProductionMaterial> productionMaterials = getProductionMaterialList(materials, production.getProductionCode());
        for (ProductionMaterial productionMaterial : productionMaterials) {
            productionMaterialDao.insertProductionMaterial(productionMaterial);
        }
        return num;
    }

    /**
     * 采购物料数据
     */
    private List<ProductionMaterial> getProductionMaterialList(String materials, String productionCode) {
        List<ProductionMaterial> productionMaterials = new ArrayList<>();
        JSONArray jsonArray = JSONArray.parseArray(materials);
        for (int i = 0; jsonArray != null && i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            ProductionMaterial productionMaterial = new ProductionMaterial();
            productionMaterial.setProductionCode(productionCode);
            productionMaterial.setMaterialCode(jsonObject.getString("materialCode"));
            productionMaterial.setQuantity(jsonObject.getInteger("quantity"));
            productionMaterials.add(productionMaterial);
        }
        return productionMaterials;
    }
}

package indi.zqc.warehouse.dao;

import indi.zqc.warehouse.model.ProductionMaterial;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Title : ProductionMaterialDao.java
 * Package : indi.zqc.warehouse.dao
 * Description : 成品物料DAO
 * Create on : 2018/2/1 20:30
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public interface ProductionMaterialDao {

    int insertProductionMaterial(ProductionMaterial production);

    int deleteProductionMaterial(@Param("productionCode") String productionCode);

    List<ProductionMaterial> selectProductionMaterial(@Param("productionCode") String productionCode);
}

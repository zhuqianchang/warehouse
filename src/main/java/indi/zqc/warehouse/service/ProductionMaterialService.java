package indi.zqc.warehouse.service;

import indi.zqc.warehouse.model.ProductionMaterial;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Title : ProductionMaterialService.java
 * Package : indi.zqc.warehouse.service
 * Description : 成品物料服务
 * Create on : 2018/2/1 20:45
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public interface ProductionMaterialService {

    List<ProductionMaterial> selectProductionMaterial(@Param("productionCode") String productionCode);
}

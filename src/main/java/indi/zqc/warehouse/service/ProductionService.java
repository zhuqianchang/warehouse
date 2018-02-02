package indi.zqc.warehouse.service;

import com.github.pagehelper.Page;
import indi.zqc.warehouse.model.Production;
import indi.zqc.warehouse.model.condition.ProductionCondition;

/**
 * Title : ProductionService.java
 * Package : indi.zqc.warehouse.service
 * Description : 成品服务
 * Create on : 2018/2/1 20:38
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public interface ProductionService {

    int insertProduction(Production production);

    int deleteProduction(String productionCode);

    int updateProduction(Production production);

    Production selectProduction(String productionCode);

    Page<Production> selectProductionPage(ProductionCondition condition);

    int saveProduction(Production production, String materials);
}

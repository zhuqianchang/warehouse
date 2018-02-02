package indi.zqc.warehouse.dao;

import com.github.pagehelper.Page;
import indi.zqc.warehouse.model.Production;
import indi.zqc.warehouse.model.condition.ProductionCondition;
import org.apache.ibatis.annotations.Param;

/**
 * Title : ProductionDao.java
 * Package : indi.zqc.warehouse.dao
 * Description : 成品DAO
 * Create on : 2018/1/28 14:46
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public interface ProductionDao {

    int insertProduction(Production production);

    int deleteProduction(@Param("productionCode") String productionCode);

    int updateProduction(Production production);

    Production selectProduction(@Param("productionCode") String productionCode);

    Page<Production> selectProductionPage(ProductionCondition condition);
}

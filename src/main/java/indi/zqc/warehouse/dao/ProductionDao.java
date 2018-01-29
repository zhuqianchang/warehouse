package indi.zqc.warehouse.dao;

import indi.zqc.warehouse.model.Production;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Title : ProductionDao.java
 * Package : indi.zqc.warehouse.dao
 * Description : 产品DAO
 * Create on : 2018/1/28 14:46
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public interface ProductionDao {

    int insertProduction(Production production);

    int deleteProduction(@Param("productionCode") String productionCode);

    int updateProduction(Production production);

    List<Production> selectProductionList(@Param("productionCode") String productionCode);
}

package indi.zqc.warehouse.dao;

import com.github.pagehelper.Page;
import indi.zqc.warehouse.model.Stock;
import indi.zqc.warehouse.model.condition.StockCondition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Title : StockDao.java
 * Package : indi.zqc.warehouse.dao
 * Description : 库存DAO
 * Create on : 2018/1/28 14:04
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public interface StockDao {

    int insertStock(Stock stock);

    int deleteStock(@Param("warehouseCode") String warehouseCode, @Param("materialCode") String materialCode);

    int updateStock(Stock stock);

    Stock selectStock(@Param("warehouseCode") String warehouseCode, @Param("materialCode") String materialCode);

    List<Stock> selectWarehouseStock(@Param("warehouseCode") String warehouseCode);

    List<Stock> selectMaterialStock(@Param("materialCode") String materialCode);

    Page<Stock> selectStockPage(StockCondition condition);
}

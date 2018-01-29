package indi.zqc.warehouse.dao;

import com.github.pagehelper.Page;
import indi.zqc.warehouse.model.Warehouse;
import indi.zqc.warehouse.model.condition.WarehouseCondition;
import org.apache.ibatis.annotations.Param;

/**
 * Title : WarehouseDao.java
 * Package : indi.zqc.warehouse.dao
 * Description : 仓库DAO
 * Create on : 2018/1/28 13:53
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public interface WarehouseDao {

    int insertWarehouse(Warehouse warehouse);

    int deleteWarehouse(@Param("warehouseCode") String warehouseCode);

    int updateWarehouse(Warehouse warehouse);

    Warehouse selectWarehouse(@Param("warehouseCode") String warehouseCode);

    Page<Warehouse> selectWarehousePage(WarehouseCondition condition);
}

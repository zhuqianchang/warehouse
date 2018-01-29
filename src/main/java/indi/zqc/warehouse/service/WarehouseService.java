package indi.zqc.warehouse.service;

import com.github.pagehelper.Page;
import indi.zqc.warehouse.model.Warehouse;
import indi.zqc.warehouse.model.condition.WarehouseCondition;

/**
 * Title : WarehouseService.java
 * Package : indi.zqc.warehouse.service
 * Description : 仓库服务
 * Create on : 2018/1/28 13:56
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public interface WarehouseService {

    int insertWarehouse(Warehouse warehouse);

    int deleteWarehouse(String warehouseCode);

    int updateWarehouse(Warehouse warehouse);

    Warehouse selectWarehouse(String warehouseCode);

    Page<Warehouse> selectWarehousePage(WarehouseCondition condition);
}

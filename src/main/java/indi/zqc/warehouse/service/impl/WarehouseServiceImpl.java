package indi.zqc.warehouse.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import indi.zqc.warehouse.dao.StockDao;
import indi.zqc.warehouse.dao.WarehouseDao;
import indi.zqc.warehouse.exception.BusinessException;
import indi.zqc.warehouse.model.Stock;
import indi.zqc.warehouse.model.Warehouse;
import indi.zqc.warehouse.model.condition.WarehouseCondition;
import indi.zqc.warehouse.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Title : WarehouseServiceImpl.java
 * Package : indi.zqc.warehouse.service.impl
 * Description : 仓库服务实现
 * Create on : 2018/1/28 13:57
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
@Transactional
@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private WarehouseDao warehouseDao;

    @Autowired
    private StockDao stockDao;

    @Override
    public int insertWarehouse(Warehouse warehouse) {
        return warehouseDao.insertWarehouse(warehouse);
    }

    @Override
    public int deleteWarehouse(String warehouseCode) {
        List<Stock> stocks = stockDao.selectWarehouseStock(warehouseCode);
        if (stocks != null && stocks.size() > 0) {
            throw new BusinessException("仓库中存在物料，不能删除");
        }
        return warehouseDao.deleteWarehouse(warehouseCode);
    }

    @Override
    public int updateWarehouse(Warehouse warehouse) {
        return warehouseDao.updateWarehouse(warehouse);
    }

    @Override
    public Warehouse selectWarehouse(String warehouseCode) {
        return warehouseDao.selectWarehouse(warehouseCode);
    }

    @Override
    public Page<Warehouse> selectWarehousePage(WarehouseCondition condition) {
        PageHelper.startPage(condition.getPageNum(), condition.getNumPerPage());
        return warehouseDao.selectWarehousePage(condition);
    }
}

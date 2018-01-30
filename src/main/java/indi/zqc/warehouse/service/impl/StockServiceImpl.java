package indi.zqc.warehouse.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import indi.zqc.warehouse.dao.MaterialDao;
import indi.zqc.warehouse.dao.StockDao;
import indi.zqc.warehouse.dao.WarehouseDao;
import indi.zqc.warehouse.model.Stock;
import indi.zqc.warehouse.model.condition.StockCondition;
import indi.zqc.warehouse.service.StockService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Title : StockServiceImpl.java
 * Package : indi.zqc.warehouse.service.impl
 * Description : 库存服务实现
 * Create on : 2018/1/28 14:07
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
@Transactional
@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockDao stockDao;

    @Autowired
    private WarehouseDao warehouseDao;

    @Autowired
    private MaterialDao materialDao;

    @Override
    public int insertStock(Stock stock) {
        return stockDao.insertStock(stock);
    }

    @Override
    public int deleteStock(String warehouseCode, String materialCode) {
        return stockDao.deleteStock(warehouseCode, materialCode);
    }

    @Override
    public int updateStock(Stock stock) {
        return stockDao.updateStock(stock);
    }

    @Override
    public Stock selectStock(String warehouseCode, String materialCode) {
        return stockDao.selectStock(warehouseCode, materialCode);
    }

    @Override
    public Page<Stock> selectStockPage(StockCondition condition) {
        PageHelper.startPage(condition.getPageNum(), condition.getNumPerPage());
        return stockDao.selectStockPage(condition);
    }
}

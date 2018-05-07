package indi.zqc.warehouse.service;

import com.github.pagehelper.Page;
import indi.zqc.warehouse.model.Stock;
import indi.zqc.warehouse.model.StockShift;
import indi.zqc.warehouse.model.condition.StockCondition;

import javax.servlet.http.HttpServletResponse;

/**
 * Title : StockService.java
 * Package : indi.zqc.warehouse.service
 * Description : 库存服务
 * Create on : 2018/1/28 14:06
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public interface StockService {

    int insertStock(Stock stock);

    int deleteStock(String warehouseCode, String materialCode);

    int updateStock(Stock stock);

    Stock selectStock(String warehouseCode, String materialCode);

    Page<Stock> selectStockPage(StockCondition condition);

    void exportStock(StockCondition condition, HttpServletResponse response);

    String shiftStock(StockShift stockShift);
}

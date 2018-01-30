package indi.zqc.warehouse.dao;

import com.github.pagehelper.Page;
import indi.zqc.warehouse.model.OperationStock;
import indi.zqc.warehouse.model.condition.OperationStockCondition;

/**
 * Title : OperationStockDao.java
 * Package : indi.zqc.warehouse.dao
 * Description : 出入库DAO
 * Create on : 2018/1/30 14:22
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public interface OperationStockDao {

    int insertOperationStock(OperationStock operationStock);

    Page<OperationStock> selectOperationStockPage(OperationStockCondition condition);
}

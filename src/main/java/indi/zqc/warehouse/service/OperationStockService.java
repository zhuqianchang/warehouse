package indi.zqc.warehouse.service;

import com.github.pagehelper.Page;
import indi.zqc.warehouse.model.OperationStock;
import indi.zqc.warehouse.model.condition.OperationStockCondition;

/**
 * Title : OperationStockService.java
 * Package : indi.zqc.warehouse.service
 * Description : 出入库服务
 * Create on : 2018/1/29 20:27
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public interface OperationStockService {

    Page<OperationStock> selectOperationStockPage(OperationStockCondition condition);

    String inputStock(String stocks, String userCode);

    String outputStock(String stocks, String userCode);
}

package indi.zqc.warehouse.service;

import indi.zqc.warehouse.model.OrderProduction;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Title : OrderProductionService.java
 * Package : indi.zqc.warehouse.service
 * Description : 订单成品服务
 * Create on : 2018/2/2 15:36
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public interface OrderProductionService {

    List<OrderProduction> selectOrderProduction(@Param("orderCode") String orderCode);
}

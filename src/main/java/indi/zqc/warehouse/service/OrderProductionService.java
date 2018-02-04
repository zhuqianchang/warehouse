package indi.zqc.warehouse.service;

import indi.zqc.warehouse.model.OrderProduction;

import java.util.List;
import java.util.Map;

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

    List<OrderProduction> selectOrderProduction(String orderCode);

    Map<String, Integer> selectOrderMaterialMap(String orderCode);

    Map<String, Integer> selectOrderMaterialMap(String[] orderCodes);
}

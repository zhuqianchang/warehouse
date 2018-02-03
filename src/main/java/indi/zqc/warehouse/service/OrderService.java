package indi.zqc.warehouse.service;

import com.github.pagehelper.Page;
import indi.zqc.warehouse.model.Order;
import indi.zqc.warehouse.model.OrderMaterial;
import indi.zqc.warehouse.model.condition.OrderCondition;

import java.util.List;

/**
 * Title : OrderService.java
 * Package : indi.zqc.warehouse.service
 * Description : 订单服务
 * Create on : 2018/2/2 15:31
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public interface OrderService {

    int insertOrder(Order order);

    int deleteOrder(String orderCode);

    int updateOrder(Order order);

    Order selectOrder(String orderCode);

    Page<Order> selectOrderPage(OrderCondition condition);

    int saveOrder(Order orders, String productions);

    List<OrderMaterial> selectOrderMaterial(String orderCode);

    int finishOrder(String orderCodes, String userCode);
}

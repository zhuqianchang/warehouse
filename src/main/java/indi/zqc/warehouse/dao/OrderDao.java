package indi.zqc.warehouse.dao;

import com.github.pagehelper.Page;
import indi.zqc.warehouse.model.Order;
import indi.zqc.warehouse.model.condition.OrderCondition;
import org.apache.ibatis.annotations.Param;

/**
 * Title : OrderDao.java
 * Package : indi.zqc.warehouse.dao
 * Description : 订单DAO
 * Create on : 2018/2/2 15:27
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public interface OrderDao {

    int insertOrder(Order order);

    int deleteOrder(@Param("orderCode") String orderCode);

    int updateOrder(Order order);

    Order selectOrder(@Param("orderCode") String orderCode);

    Page<Order> selectOrderPage(OrderCondition condition);
}

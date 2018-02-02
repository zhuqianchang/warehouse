package indi.zqc.warehouse.dao;

import indi.zqc.warehouse.model.OrderProduction;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Title : OrderProductionDao.java
 * Package : indi.zqc.warehouse.dao
 * Description : 订单成品DAO
 * Create on : 2018/2/2 15:27
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public interface OrderProductionDao {

    int insertOrderProduction(OrderProduction orderProduction);

    int deleteOrderProduction(@Param("orderCode") String orderCode);

    List<OrderProduction> selectOrderProduction(@Param("orderCode") String orderCode);
}

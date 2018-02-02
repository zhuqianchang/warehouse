package indi.zqc.warehouse.service.impl;

import indi.zqc.warehouse.dao.OrderProductionDao;
import indi.zqc.warehouse.model.OrderProduction;
import indi.zqc.warehouse.service.OrderProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Title : OrderProductionServiceImpl.java
 * Package : indi.zqc.warehouse.service.impl
 * Description : 订单成品服务实现
 * Create on : 2018/2/2 15:35
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
@Transactional
@Service
public class OrderProductionServiceImpl implements OrderProductionService {

    @Autowired
    private OrderProductionDao orderProductionDao;

    @Override
    public List<OrderProduction> selectOrderProduction(String productionCode) {
        return orderProductionDao.selectOrderProduction(productionCode);
    }
}

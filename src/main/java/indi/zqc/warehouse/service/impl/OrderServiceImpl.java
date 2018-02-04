package indi.zqc.warehouse.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import indi.zqc.warehouse.constant.Constants;
import indi.zqc.warehouse.dao.MaterialDao;
import indi.zqc.warehouse.dao.OrderDao;
import indi.zqc.warehouse.dao.OrderProductionDao;
import indi.zqc.warehouse.enums.OrderStatus;
import indi.zqc.warehouse.exception.BusinessException;
import indi.zqc.warehouse.model.Order;
import indi.zqc.warehouse.model.OrderMaterial;
import indi.zqc.warehouse.model.OrderProduction;
import indi.zqc.warehouse.model.condition.OrderCondition;
import indi.zqc.warehouse.service.OrderProductionService;
import indi.zqc.warehouse.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Title : OrderServiceImpl.java
 * Package : indi.zqc.warehouse.service.impl
 * Description : 订单服务实现
 * Create on : 2018/2/2 15:32
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
@Transactional
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderProductionDao orderProductionDao;

    @Autowired
    private MaterialDao materialDao;

    @Autowired
    private OrderProductionService orderProductionService;

    @Override
    public int insertOrder(Order order) {
        return orderDao.insertOrder(order);
    }

    @Override
    public int deleteOrder(String orderCodes) {
        int num = 0;
        for (String orderCode : orderCodes.split(",")) {
            orderProductionDao.deleteOrderProduction(orderCode);
            num += orderDao.deleteOrder(orderCode);
        }
        return num;
    }

    @Override
    public int updateOrder(Order order) {
        return orderDao.updateOrder(order);
    }

    @Override
    public Order selectOrder(String orderCode) {
        return orderDao.selectOrder(orderCode);
    }

    @Override
    public Page<Order> selectOrderPage(OrderCondition condition) {
        PageHelper.startPage(condition.getPageNum(), condition.getNumPerPage());
        return orderDao.selectOrderPage(condition);
    }

    @Override
    public int finishOrder(String orderCodes, String userCode) {
        int num = 0;
        for (String orderCode : orderCodes.split(",")) {
            Order order = selectOrder(orderCode);
            if (order == null) {
                throw new BusinessException(String.format("订单[%s]不存在", orderCode));
            }
            if (StringUtils.equals(order.getOrderStatus(), OrderStatus.FINISHED.getKey())) {
                throw new BusinessException(String.format("订单[%s]已完成", orderCode));
            }
            order.setOrderStatus(OrderStatus.FINISHED.getKey());
            order.setModifyUser(userCode);
            order.setModifyDateTime(new Date());
            num += updateOrder(order);
        }
        return num;
    }

    @Override
    public int saveOrder(Order order, String productions) {
        Order oldOrder = selectOrder(order.getOrderCode());
        int num;
        if (oldOrder == null) {
            order.setOrderStatus(OrderStatus.CREATED.getKey());
            num = insertOrder(order);
        } else {
            num = updateOrder(order);
        }
        orderProductionDao.deleteOrderProduction(order.getOrderCode());
        List<OrderProduction> orderProductions = getOrderProductionList(productions, order.getOrderCode());
        for (OrderProduction orderProduction : orderProductions) {
            orderProductionDao.insertOrderProduction(orderProduction);
        }
        return num;
    }

    /**
     * 采购物料数据
     */
    private List<OrderProduction> getOrderProductionList(String productions, String orderCode) {
        List<OrderProduction> orderProductions = new ArrayList<>();
        JSONArray jsonArray = JSONArray.parseArray(productions);
        for (int i = 0; jsonArray != null && i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            OrderProduction orderProduction = new OrderProduction();
            orderProduction.setOrderCode(orderCode);
            orderProduction.setProductionCode(jsonObject.getString("productionCode"));
            orderProduction.setQuantity(jsonObject.getInteger("quantity"));
            orderProductions.add(orderProduction);
        }
        return orderProductions;
    }

    @Override
    public List<OrderMaterial> selectOrderMaterial(String orderCode) {
        List<OrderMaterial> orderMaterials = new ArrayList<>();
        Map<String, Integer> materialMap = orderProductionService.selectOrderMaterialMap(orderCode);
        Iterator<Map.Entry<String, Integer>> it = materialMap.entrySet().iterator();
        while (it.hasNext()) {
            OrderMaterial orderMaterial = new OrderMaterial();
            Map.Entry<String, Integer> entry = it.next();
            orderMaterial.setOrderCode(orderCode);
            orderMaterial.setMaterialCode(entry.getKey());
            orderMaterial.setMaterialText(materialDao.selectMaterial(entry.getKey()).getMaterialText());
            orderMaterial.setQuantity(entry.getValue());
            orderMaterials.add(orderMaterial);
        }
        return orderMaterials;
    }

    @Override
    public List<Order> selectOrders(String orderCodes) {
        List<Order> orders = new ArrayList<>();
        if (StringUtils.isNotBlank(orderCodes)) {
            for (String orderCode : orderCodes.split(Constants.SEPARATOR)) {
                Order order = selectOrder(orderCode);
                if (order != null) {
                    orders.add(order);
                }
            }
        }
        return orders;
    }

    @Override
    public List<OrderMaterial> selectOrderMaterials(String orderCodes) {
        List<OrderMaterial> orderMaterials = new ArrayList<>();
        if (StringUtils.isNotBlank(orderCodes)) {
            Map<String, Integer> materialMap = orderProductionService.selectOrderMaterialMap(orderCodes.split(Constants.SEPARATOR));
            Iterator<Map.Entry<String, Integer>> it = materialMap.entrySet().iterator();
            while (it.hasNext()) {
                OrderMaterial orderMaterial = new OrderMaterial();
                Map.Entry<String, Integer> entry = it.next();
                orderMaterial.setMaterialCode(entry.getKey());
                orderMaterial.setMaterialText(materialDao.selectMaterial(entry.getKey()).getMaterialText());
                orderMaterial.setQuantity(entry.getValue());
                orderMaterials.add(orderMaterial);
            }
        }
        return orderMaterials;
    }
}

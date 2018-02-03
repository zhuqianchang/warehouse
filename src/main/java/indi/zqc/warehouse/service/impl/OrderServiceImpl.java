package indi.zqc.warehouse.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import indi.zqc.warehouse.dao.MaterialDao;
import indi.zqc.warehouse.dao.OrderDao;
import indi.zqc.warehouse.dao.OrderProductionDao;
import indi.zqc.warehouse.dao.ProductionMaterialDao;
import indi.zqc.warehouse.enums.OrderStatus;
import indi.zqc.warehouse.exception.BusinessException;
import indi.zqc.warehouse.model.Order;
import indi.zqc.warehouse.model.OrderMaterial;
import indi.zqc.warehouse.model.OrderProduction;
import indi.zqc.warehouse.model.ProductionMaterial;
import indi.zqc.warehouse.model.condition.OrderCondition;
import indi.zqc.warehouse.service.OrderService;
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
    private ProductionMaterialDao productionMaterialDao;

    @Autowired
    private MaterialDao materialDao;

    @Override
    public int insertOrder(Order order) {
        return orderDao.insertOrder(order);
    }

    @Override
    public int deleteOrder(String orderCode) {
        int num = orderDao.deleteOrder(orderCode);
        orderProductionDao.deleteOrderProduction(orderCode);
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
    public int finishOrder(String orderCode, String userCode) {
        Order order = selectOrder(orderCode);
        if (order == null) {
            throw new BusinessException("订单不存在");
        }
        order.setOrderStatus(OrderStatus.FINISHED.getKey());
        order.setModifyUser(userCode);
        order.setModifyDateTime(new Date());
        return updateOrder(order);
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
        //订单中的成品
        List<OrderProduction> orderProductions = orderProductionDao.selectOrderProduction(orderCode);
        Map<String, Integer> materialMap = new HashMap<>();
        for (OrderProduction orderProduction : orderProductions) {
            //成品中的物料
            List<ProductionMaterial> productionMaterials = productionMaterialDao.selectProductionMaterial(orderProduction.getProductionCode());
            for (ProductionMaterial productionMaterial : productionMaterials) {
                String materialCode = productionMaterial.getMaterialCode();
                if (materialMap.containsKey(materialCode)) {
                    materialMap.put(materialCode, materialMap.get(materialCode) + productionMaterial.getQuantity() * orderProduction.getQuantity());
                } else {
                    materialMap.put(materialCode, productionMaterial.getQuantity() * orderProduction.getQuantity());
                }
            }
        }
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
}

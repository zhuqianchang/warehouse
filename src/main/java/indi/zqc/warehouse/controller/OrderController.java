package indi.zqc.warehouse.controller;

import com.github.pagehelper.Page;
import indi.zqc.warehouse.enums.OrderStatus;
import indi.zqc.warehouse.model.DWZResult;
import indi.zqc.warehouse.model.Order;
import indi.zqc.warehouse.model.condition.OrderCondition;
import indi.zqc.warehouse.service.OrderProductionService;
import indi.zqc.warehouse.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Title : OrderController.java
 * Package : indi.zqc.warehouse.controller
 * Description : 订单
 * Create on : 2018/2/2 15:30
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
@Controller
@RequestMapping(value = "/order/*")
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderProductionService orderProductionService;

    @RequestMapping("/list")
    public String orderList(Model model, OrderCondition condition, String navTabId) {
        Page<Order> page = orderService.selectOrderPage(condition);
        condition.setData(page);
        condition.setTotalCount(page.getTotal());
        model.addAttribute(PAGE, condition);
        model.addAttribute("orderStatus", OrderStatus.values());
        model.addAttribute(NAVTABID, navTabId);
        return "order/order_list";
    }

    @RequestMapping("/add")
    public String addOrder(Model model, String navTabId) {
        model.addAttribute(NAVTABID, navTabId);
        model.addAttribute("orderStatus", OrderStatus.values());
        return "order/order_add";
    }

    @RequestMapping("/edit")
    public String editOrder(Model model, String orderCode, String navTabId) {
        model.addAttribute("order", orderService.selectOrder(orderCode));
        model.addAttribute("productions", orderProductionService.selectOrderProduction(orderCode));
        model.addAttribute("orderStatus", OrderStatus.values());
        model.addAttribute(NAVTABID, navTabId);
        return "order/order_add";
    }

    @RequestMapping("/view")
    public String viewOrder(Model model, String orderCode, String navTabId) {
        model.addAttribute("order", orderService.selectOrder(orderCode));
        model.addAttribute("productions", orderProductionService.selectOrderProduction(orderCode));
        model.addAttribute("materials", orderService.selectOrderMaterial(orderCode));
        model.addAttribute(NAVTABID, navTabId);
        return "order/order_view";
    }

    @RequestMapping("/save")
    @ResponseBody
    public DWZResult saveOrder(Order order, String productions) {
        setCreateInfo(order);
        orderService.saveOrder(order, productions);
        return dialogAjaxDone();
    }

    @RequestMapping("/calculate")
    public String calculateOrder(Model model, String orderCodes, String navTabId) {
        model.addAttribute("orderCodes", orderCodes);
        model.addAttribute("orders", orderService.selectOrders(orderCodes));
        model.addAttribute("materials", orderService.selectOrderMaterials(orderCodes));
        model.addAttribute(NAVTABID, navTabId);
        return "order/order_calculate";
    }

    @RequestMapping("/finish")
    @ResponseBody
    public DWZResult finishOrder(String orderCodes) {
        orderService.finishOrder(orderCodes, getCurrentUserCode());
        return ajaxDone();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public DWZResult deleteOrder(String orderCodes) {
        orderService.deleteOrder(orderCodes);
        return ajaxDone();
    }

    @RequestMapping("/verify")
    @ResponseBody
    public boolean verifyOrderCode(String orderCode) {
        return orderService.selectOrder(orderCode) == null;
    }
}

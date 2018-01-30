package indi.zqc.warehouse.controller;

import com.github.pagehelper.Page;
import indi.zqc.warehouse.enums.OperationType;
import indi.zqc.warehouse.model.DWZResult;
import indi.zqc.warehouse.model.OperationStock;
import indi.zqc.warehouse.model.condition.OperationStockCondition;
import indi.zqc.warehouse.service.OperationStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Title : OperationStockController.java
 * Package : indi.zqc.warehouse.controller
 * Description : 出入库管理
 * Create on : 2018/1/29 20:16
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
@Controller
@RequestMapping("/operationStock/*")
public class OperationStockController extends BaseController {

    @Autowired
    private OperationStockService operationStockService;

    @RequestMapping("/input")
    public String inputStock(Model model, String navTabId) {
        model.addAttribute(NAVTABID, navTabId);
        return "operationStock/operationStock_input";
    }

    @RequestMapping("/inputStock")
    @ResponseBody
    public DWZResult inputStock(String stocks) {
        return ajaxDone(operationStockService.inputStock(stocks, getCurrentUserCode()));
    }

    @RequestMapping("/output")
    public String outputStock(Model model, String navTabId) {
        model.addAttribute(NAVTABID, navTabId);
        return "operationStock/operationStock_output";
    }

    @RequestMapping("/outputStock")
    @ResponseBody
    public DWZResult outputStock(String stocks) {
        return ajaxDone(operationStockService.outputStock(stocks, getCurrentUserCode()));
    }

    @RequestMapping("/record")
    public String record(Model model, OperationStockCondition condition, String navTabId) {
        Page<OperationStock> page = operationStockService.selectOperationStockPage(condition);
        condition.setData(page);
        condition.setTotalCount(page.getTotal());
        model.addAttribute(PAGE, condition);
        model.addAttribute("operationTypes", OperationType.values());
        model.addAttribute(NAVTABID, navTabId);
        return "operationStock/operationStock_record";
    }

}

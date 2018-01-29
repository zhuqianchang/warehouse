package indi.zqc.warehouse.controller;

import com.github.pagehelper.Page;
import indi.zqc.warehouse.model.DWZResult;
import indi.zqc.warehouse.model.Warehouse;
import indi.zqc.warehouse.model.condition.WarehouseCondition;
import indi.zqc.warehouse.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Title : WarehouseController.java
 * Package : indi.zqc.warehouse.controller
 * Description : 仓库
 * Create on : 2018/1/28 17:19
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
@Controller
@RequestMapping(value = "/warehouse/*")
public class WarehouseController extends BaseController {

    @Autowired
    private WarehouseService warehouseService;

    @RequestMapping("/list")
    public String warehouseList(Model model, WarehouseCondition condition, String navTabId) {
        Page<Warehouse> page = warehouseService.selectWarehousePage(condition);
        condition.setData(page);
        condition.setTotalCount(page.getTotal());
        model.addAttribute(PAGE, condition);
        model.addAttribute(NAVTABID, navTabId);
        return "warehouse/warehouse_list";
    }

    @RequestMapping("/add")
    public String addWarehouse(Model model, String navTabId) {
        model.addAttribute(NAVTABID, navTabId);
        return "warehouse/warehouse_add";
    }

    @RequestMapping("/edit")
    public String editWarehouse(Model model, String warehouseCode, String navTabId) {
        model.addAttribute("warehouse", warehouseService.selectWarehouse(warehouseCode));
        model.addAttribute(NAVTABID, navTabId);
        return "warehouse/warehouse_add";
    }

    @RequestMapping("/view")
    public String viewWarehouse(Model model, String warehouseCode, String navTabId) {
        model.addAttribute("warehouse", warehouseService.selectWarehouse(warehouseCode));
        model.addAttribute(NAVTABID, navTabId);
        return "warehouse/warehouse_view";
    }

    @RequestMapping("/save")
    @ResponseBody
    public DWZResult saveWarehouse(Warehouse warehouse, String navTabId, HttpServletRequest request) {
        Warehouse oldWarehouse = warehouseService.selectWarehouse(warehouse.getWarehouseCode());
        if (oldWarehouse == null) {
            setCreateInfo(warehouse);
            warehouseService.insertWarehouse(warehouse);
        } else {
            setModifyInfo(warehouse);
            warehouseService.updateWarehouse(warehouse);
        }
        String forwardUrl = getForwardUrl(request) + "/warehouse/list?navTabId=" + navTabId;
        return dialogAjaxDone(navTabId, forwardUrl);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public DWZResult deleteWarehouse(String warehouseCode) {
        warehouseService.deleteWarehouse(warehouseCode);
        return ajaxDone();
    }

    @RequestMapping("/verify")
    @ResponseBody
    public boolean verifyWarehouseCode(String warehouseCode) {
        return warehouseService.selectWarehouse(warehouseCode) == null;
    }

    @RequestMapping("/lookup")
    public String warehouseLookup(Model model, WarehouseCondition condition) {
        Page<Warehouse> page = warehouseService.selectWarehousePage(condition);
        condition.setData(page);
        condition.setTotalCount(page.getTotal());
        model.addAttribute(PAGE, condition);
        return "warehouse/warehouse_lookup";
    }
}

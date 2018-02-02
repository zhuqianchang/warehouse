package indi.zqc.warehouse.controller;

import com.github.pagehelper.Page;
import indi.zqc.warehouse.model.DWZResult;
import indi.zqc.warehouse.model.Production;
import indi.zqc.warehouse.model.condition.ProductionCondition;
import indi.zqc.warehouse.service.ProductionMaterialService;
import indi.zqc.warehouse.service.ProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Title : ProductionController.java
 * Package : indi.zqc.warehouse.controller
 * Description : 成品
 * Create on : 2018/1/29 19:44
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
@Controller
@RequestMapping(value = "/production/*")
public class ProductionController extends BaseController {

    @Autowired
    private ProductionService productionService;

    @Autowired
    private ProductionMaterialService productionMaterialService;

    @RequestMapping("/list")
    public String productionList(Model model, ProductionCondition condition, String navTabId) {
        Page<Production> page = productionService.selectProductionPage(condition);
        condition.setData(page);
        condition.setTotalCount(page.getTotal());
        model.addAttribute(PAGE, condition);
        model.addAttribute(NAVTABID, navTabId);
        return "production/production_list";
    }

    @RequestMapping("/add")
    public String addProduction(Model model, String navTabId) {
        model.addAttribute(NAVTABID, navTabId);
        return "production/production_add";
    }

    @RequestMapping("/edit")
    public String editProduction(Model model, String productionCode, String navTabId) {
        model.addAttribute("production", productionService.selectProduction(productionCode));
        model.addAttribute("materials", productionMaterialService.selectProductionMaterial(productionCode));
        model.addAttribute(NAVTABID, navTabId);
        return "production/production_add";
    }

    @RequestMapping("/view")
    public String viewProduction(Model model, String productionCode, String navTabId) {
        model.addAttribute("production", productionService.selectProduction(productionCode));
        model.addAttribute("materials", productionMaterialService.selectProductionMaterial(productionCode));
        model.addAttribute(NAVTABID, navTabId);
        return "production/production_view";
    }

    @RequestMapping("/save")
    @ResponseBody
    public DWZResult saveProduction(Production production, String materials, String navTabId, HttpServletRequest request) {
        setCreateInfo(production);
        productionService.saveProduction(production, materials);
        String forwardUrl = getForwardUrl(request) + "/production/list?navTabId=" + navTabId;
        return dialogAjaxDone(navTabId, forwardUrl);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public DWZResult deleteProduction(String productionCode) {
        productionService.deleteProduction(productionCode);
        return ajaxDone();
    }

    @RequestMapping("/verify")
    @ResponseBody
    public boolean verifyProductionCode(String productionCode) {
        return productionService.selectProduction(productionCode) == null;
    }

    @RequestMapping("/lookup")
    public String productionLookup(Model model, ProductionCondition condition) {
        Page<Production> page = productionService.selectProductionPage(condition);
        condition.setData(page);
        condition.setTotalCount(page.getTotal());
        model.addAttribute(PAGE, condition);
        return "production/production_lookup";
    }
}

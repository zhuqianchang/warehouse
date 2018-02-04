package indi.zqc.warehouse.controller;

import com.github.pagehelper.Page;
import indi.zqc.warehouse.enums.PurchaseStatus;
import indi.zqc.warehouse.enums.PurchaseType;
import indi.zqc.warehouse.model.DWZResult;
import indi.zqc.warehouse.model.Purchase;
import indi.zqc.warehouse.model.condition.PurchaseCondition;
import indi.zqc.warehouse.service.PurchaseMaterialService;
import indi.zqc.warehouse.service.PurchaseOrderService;
import indi.zqc.warehouse.service.PurchaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Title : PurchaseController.java
 * Package : indi.zqc.warehouse.controller
 * Description : 采购清单
 * Create on : 2018/2/1 17:07
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
@Controller
@RequestMapping(value = "/purchase/*")
public class PurchaseController extends BaseController {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private PurchaseMaterialService purchaseMaterialService;

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @RequestMapping("/list")
    public String purchaseList(Model model, PurchaseCondition condition, String navTabId) {
        Page<Purchase> page = purchaseService.selectPurchasePage(condition);
        condition.setData(page);
        condition.setTotalCount(page.getTotal());
        model.addAttribute(PAGE, condition);
        model.addAttribute("purchaseTypes", PurchaseType.values());
        model.addAttribute("purchaseStatus", PurchaseStatus.values());
        model.addAttribute(NAVTABID, navTabId);
        return "purchase/purchase_list";
    }

    @RequestMapping("/add")
    public String addPurchase(Model model, String navTabId) {
        model.addAttribute("purchaseTypes", PurchaseType.values());
        model.addAttribute("purchaseStatus", PurchaseStatus.values());
        model.addAttribute(NAVTABID, navTabId);
        return "purchase/purchase_add";
    }

    @RequestMapping("/edit")
    public String editPurchase(Model model, String purchaseCode, String navTabId) {
        Purchase purchase = purchaseService.selectPurchase(purchaseCode);
        model.addAttribute("purchase", purchase);
        model.addAttribute("materials", purchaseMaterialService.selectPurchaseMaterial(purchaseCode));
        model.addAttribute("purchaseTypes", PurchaseType.values());
        model.addAttribute("purchaseStatus", PurchaseStatus.values());
        model.addAttribute(NAVTABID, navTabId);
        if (StringUtils.equals(purchase.getPurchaseType(), PurchaseType.AUTO.getKey())) {
            model.addAttribute("orders", purchaseOrderService.selectPurchaseOrder(purchaseCode));
            model.addAttribute("message", "自动生成的采购清单不能修改");
            return "purchase/purchase_order_view";
        } else {
            return "purchase/purchase_add";
        }
    }

    @RequestMapping("/view")
    public String viewPurchase(Model model, String purchaseCode, String navTabId) {
        Purchase purchase = purchaseService.selectPurchase(purchaseCode);
        model.addAttribute("purchase", purchase);
        model.addAttribute("materials", purchaseMaterialService.selectPurchaseMaterial(purchaseCode));
        model.addAttribute(NAVTABID, navTabId);
        if (StringUtils.equals(purchase.getPurchaseType(), PurchaseType.AUTO.getKey())) {
            model.addAttribute("orders", purchaseOrderService.selectPurchaseOrder(purchaseCode));
            return "purchase/purchase_order_view";
        } else {
            return "purchase/purchase_material_view";
        }
    }

    @RequestMapping("/save")
    @ResponseBody
    public DWZResult savePurchase(Purchase purchase, String materials, String navTabId, HttpServletRequest request) {
        setCreateInfo(purchase);
        purchaseService.savePurchase(purchase, materials);
        String forwardUrl = getForwardUrl(request) + "/purchase/list?navTabId=" + navTabId;
        return dialogAjaxDone(navTabId, forwardUrl);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public DWZResult deletePurchase(String purchaseCode) {
        purchaseService.deletePurchase(purchaseCode);
        return ajaxDone();
    }

    @RequestMapping("/produce")
    @ResponseBody
    public DWZResult producePurchase(String orderCodes) {
        return ajaxDone(purchaseService.producePurchase(orderCodes, getCurrentUserCode()));
    }

    @RequestMapping("/finish")
    @ResponseBody
    public DWZResult finishPurchase(String purchaseCode) {
        purchaseService.finishPurchase(purchaseCode, getCurrentUserCode());
        return ajaxDone();
    }

    @RequestMapping("/verify")
    @ResponseBody
    public boolean verifyPurchaseCode(String purchaseCode) {
        return purchaseService.selectPurchase(purchaseCode) == null;
    }

    @RequestMapping("/export")
    @ResponseBody
    public void exportPurchase(String purchaseCode, HttpServletResponse response) {
        purchaseService.exportPurchase(purchaseCode, response);
    }
}

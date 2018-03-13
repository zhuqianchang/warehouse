package indi.zqc.warehouse.controller;

import com.github.pagehelper.Page;
import indi.zqc.warehouse.model.DWZResult;
import indi.zqc.warehouse.model.Produce;
import indi.zqc.warehouse.model.condition.ProduceCondition;
import indi.zqc.warehouse.service.ProduceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * Title : ProduceController.java
 * Package : indi.zqc.produce.controller
 * Description : 生产
 * Create on : 2018/3/12 20:35
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
@Controller
@RequestMapping(value = "/produce/*")
public class ProduceController extends BaseController {

    @Autowired
    private ProduceService produceService;

    @RequestMapping("/list")
    public String produceList(Model model, ProduceCondition condition, String navTabId) {
        Page<Produce> page = produceService.selectProducePage(condition);
        condition.setData(page);
        condition.setTotalCount(page.getTotal());
        model.addAttribute(PAGE, condition);
        model.addAttribute(NAVTABID, navTabId);
        return "produce/produce_list";
    }

    @RequestMapping("/add")
    public String addProduce(Model model, String navTabId) {
        model.addAttribute(NAVTABID, navTabId);
        return "produce/produce_add";
    }

    @RequestMapping("/edit")
    public String editProduce(Model model, String produceCode, String navTabId) {
        model.addAttribute("produce", produceService.selectProduce(produceCode));
        model.addAttribute(NAVTABID, navTabId);
        return "produce/produce_add";
    }

    @RequestMapping("/save")
    @ResponseBody
    public DWZResult saveProduce(Produce produce) {
        Produce oldProduce = produceService.selectProduce(produce.getProduceCode());
        if (oldProduce == null) {
            setCreateInfo(produce);
            produceService.insertProduce(produce);
        } else {
            setModifyInfo(produce);
            produceService.updateProduce(produce);
        }
        return dialogAjaxDone();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public DWZResult deleteProduce(String produceCode) {
        produceService.deleteProduce(produceCode);
        return ajaxDone();
    }

    @RequestMapping("/export")
    @ResponseBody
    public void exportProduce(ProduceCondition condition, HttpServletResponse response) {
        produceService.exportProduce(condition, response);
    }
}

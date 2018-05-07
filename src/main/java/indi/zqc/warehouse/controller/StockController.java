package indi.zqc.warehouse.controller;

import com.github.pagehelper.Page;
import indi.zqc.warehouse.constant.Constants;
import indi.zqc.warehouse.model.DWZResult;
import indi.zqc.warehouse.model.Stock;
import indi.zqc.warehouse.model.StockShift;
import indi.zqc.warehouse.model.condition.StockCondition;
import indi.zqc.warehouse.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Title : StockController.java
 * Package : indi.zqc.warehouse.controller
 * Description : 库存
 * Create on : 2018/1/28 17:43
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
@Controller
@RequestMapping(value = "/stock/*")
public class StockController extends BaseController {

    @Autowired
    private StockService stockService;

    @RequestMapping("/list")
    public String stockList(Model model, StockCondition condition, String navTabId) {
        Page<Stock> page = stockService.selectStockPage(condition);
        condition.setData(page);
        condition.setTotalCount(page.getTotal());
        model.addAttribute(PAGE, condition);
        model.addAttribute(NAVTABID, navTabId);
        return "stock/stock_list";
    }

    @RequestMapping("/add")
    public String addStock(Model model, String navTabId) {
        model.addAttribute(NAVTABID, navTabId);
        return "stock/stock_add";
    }

    @RequestMapping("/edit")
    public String editStock(Model model, String stockCode, String navTabId) {
        model.addAttribute("stock", stockService.selectStock(stockCode.split(Constants.SEPARATOR)[0], stockCode.split(Constants.SEPARATOR)[1]));
        model.addAttribute(NAVTABID, navTabId);
        return "stock/stock_edit";
    }

    @RequestMapping("/view")
    public String viewStock(Model model, String stockCode, String navTabId) {
        model.addAttribute("stock", stockService.selectStock(stockCode.split(Constants.SEPARATOR)[0], stockCode.split(Constants.SEPARATOR)[1]));
        model.addAttribute(NAVTABID, navTabId);
        return "stock/stock_view";
    }

    @RequestMapping("/save")
    @ResponseBody
    public DWZResult saveStock(Stock stock) {
        Stock oldStock = stockService.selectStock(stock.getWarehouseCode(), stock.getMaterialCode());
        if (oldStock == null) {
            setCreateInfo(stock);
            stock.setEditor(getCurrentUserCode());
            stock.setEditTime(new Date());
            stockService.insertStock(stock);
        } else {
            oldStock.setEditor(getCurrentUserCode());
            oldStock.setEditTime(new Date());
            oldStock.setStock(stock.getStock());
            stockService.updateStock(oldStock);
        }
        return dialogAjaxDone();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public DWZResult deleteStock(String stockCode) {
        stockService.deleteStock(stockCode.split(Constants.SEPARATOR)[0], stockCode.split(Constants.SEPARATOR)[1]);
        return ajaxDone();
    }

    @RequestMapping("/verify")
    @ResponseBody
    public boolean verifyStockCode(String warehouseCode, String materialCode) {
        return stockService.selectStock(warehouseCode, materialCode) == null;
    }

    @RequestMapping("/export")
    @ResponseBody
    public void exportStock(StockCondition condition, HttpServletResponse response) {
        stockService.exportStock(condition, response);
    }

    @RequestMapping("/shift")
    public String shiftStock(Model model, String stockCode, String navTabId) {
        model.addAttribute("stock", stockService.selectStock(stockCode.split(Constants.SEPARATOR)[0], stockCode.split(Constants.SEPARATOR)[1]));
        model.addAttribute(NAVTABID, navTabId);
        return "stock/stock_shift";
    }

    @RequestMapping("/doShift")
    @ResponseBody
    public DWZResult shiftStock(StockShift stockShift) {
        setCreateInfo(stockShift);
        return dialogAjaxDone(stockService.shiftStock(stockShift));
    }
}

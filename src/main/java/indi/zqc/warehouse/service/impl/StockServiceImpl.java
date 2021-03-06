package indi.zqc.warehouse.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import indi.zqc.warehouse.constant.Constants;
import indi.zqc.warehouse.dao.StockDao;
import indi.zqc.warehouse.enums.OperationType;
import indi.zqc.warehouse.exception.BusinessException;
import indi.zqc.warehouse.model.OperationStock;
import indi.zqc.warehouse.model.Stock;
import indi.zqc.warehouse.model.StockShift;
import indi.zqc.warehouse.model.condition.StockCondition;
import indi.zqc.warehouse.service.OperationStockService;
import indi.zqc.warehouse.service.StockService;
import indi.zqc.warehouse.util.ExcelUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

/**
 * Title : StockServiceImpl.java
 * Package : indi.zqc.warehouse.service.impl
 * Description : 库存服务实现
 * Create on : 2018/1/28 14:07
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
@Transactional
@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockDao stockDao;

    @Autowired
    private OperationStockService operationStockService;

    @Override
    public int insertStock(Stock stock) {
        return stockDao.insertStock(stock);
    }

    @Override
    public int deleteStock(String warehouseCode, String materialCode) {
        return stockDao.deleteStock(warehouseCode, materialCode);
    }

    @Override
    public int updateStock(Stock stock) {
        return stockDao.updateStock(stock);
    }

    @Override
    public Stock selectStock(String warehouseCode, String materialCode) {
        return stockDao.selectStock(warehouseCode, materialCode);
    }

    @Override
    public Page<Stock> selectStockPage(StockCondition condition) {
        PageHelper.startPage(condition.getPageNum(), condition.getNumPerPage());
        return stockDao.selectStockPage(condition);
    }

    @Override
    public void exportStock(StockCondition condition, HttpServletResponse response) {
        Page<Stock> stocks = stockDao.selectStockPage(condition);
        OutputStream os = null;
        InputStream is = null;
        XSSFWorkbook workbook;
        XSSFSheet sheet;
        try {
            response.setContentType("application/vnd.ms-excel");
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(("库存清单").getBytes(), "ISO8859-1") + ".xlsx");
            os = response.getOutputStream();
            is = new ClassPathResource(Constants.TEMPLATE_STOCK).getInputStream();
            workbook = new XSSFWorkbook(is);
            sheet = workbook.getSheetAt(0);
            XSSFRow styleRow = sheet.getRow(1);
            //库存信息
            for (int i = 0; i < stocks.size(); i++) {
                Stock stock = stocks.get(i);
                XSSFRow row = sheet.createRow(i + 2);
                ExcelUtils.setCell(row, 0, styleRow.getCell(0).getCellStyle(), i + 1);
                ExcelUtils.setCell(row, 1, styleRow.getCell(1).getCellStyle(), stock.getWarehouseCode());
                ExcelUtils.setCell(row, 2, styleRow.getCell(2).getCellStyle(), stock.getWarehouseText());
                ExcelUtils.setCell(row, 3, styleRow.getCell(3).getCellStyle(), stock.getMaterialCode());
                ExcelUtils.setCell(row, 4, styleRow.getCell(4).getCellStyle(), stock.getMaterialText());
                ExcelUtils.setCell(row, 5, styleRow.getCell(5).getCellStyle(), stock.getStock());
                ExcelUtils.setCell(row, 6, styleRow.getCell(6).getCellStyle(), stock.getCreateUserText());
                ExcelUtils.setCell(row, 7, styleRow.getCell(7).getCellStyle(), stock.getCreateDateTime());
                ExcelUtils.setCell(row, 8, styleRow.getCell(8).getCellStyle(), stock.getModifyUserText());
                ExcelUtils.setCell(row, 9, styleRow.getCell(9).getCellStyle(), stock.getModifyDateTime());
                ExcelUtils.setCell(row, 10, styleRow.getCell(10).getCellStyle(), stock.getEditorText());
                ExcelUtils.setCell(row, 11, styleRow.getCell(11).getCellStyle(), stock.getEditTime());
            }
            workbook.write(os);
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * 移库
     * 先出库 再入库
     * @param stockShift
     */
    @Override
    public String shiftStock(StockShift stockShift) {
        Stock srcStock = stockDao.selectStock(stockShift.getSrcWarehouseCode(), stockShift.getMaterialCode());
        if (srcStock == null) {
            throw new BusinessException(String.format("仓库[%s]与物料[%s]的关系不存在，请先维护", stockShift.getSrcWarehouseCode(), stockShift.getMaterialCode()));
        } else if (srcStock.getStock() < stockShift.getShiftNum()) {
            throw new BusinessException(String.format("仓库[%s]中的物料[%s]库存不足", stockShift.getSrcWarehouseCode(), stockShift.getMaterialCode()));
        }
        Stock targetStock = stockDao.selectStock(stockShift.getTargetWarehouseCode(), stockShift.getMaterialCode());
        if (targetStock == null) {
            throw new BusinessException(String.format("仓库[%s]与物料[%s]的关系不存在，请先维护", stockShift.getTargetWarehouseCode(), stockShift.getMaterialCode()));
        }
        String receiptCode = newReceiptCode();
        //出库
        OperationStock stock = new OperationStock();
        stock.setReceiptCode(receiptCode);
        stock.setWarehouseCode(stockShift.getSrcWarehouseCode());
        stock.setMaterialCode(stockShift.getMaterialCode());
        stock.setOperationType(OperationType.OUTPUT.getKey());
        stock.setQuantity(stockShift.getShiftNum());
        stock.setCreateUser(stockShift.getCreateUser());
        stock.setCreateDateTime(stockShift.getCreateDateTime());
        stock.setModifyUser(stockShift.getModifyUser());
        stock.setModifyDateTime(stockShift.getModifyDateTime());
        operationStockService.outputStock(stock);
        //入库操作
        stock.setWarehouseCode(stockShift.getTargetWarehouseCode());
        stock.setOperationType(OperationType.INPUT.getKey());
        operationStockService.inputStock(stock);

        return receiptCode;
    }

    /**
     * 单据编号
     */
    private String newReceiptCode() {
        return Constants.DJ_PREFIX + DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
    }
}

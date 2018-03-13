package indi.zqc.warehouse.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import indi.zqc.warehouse.constant.Constants;
import indi.zqc.warehouse.dao.ProduceDao;
import indi.zqc.warehouse.model.Produce;
import indi.zqc.warehouse.model.condition.ProduceCondition;
import indi.zqc.warehouse.service.ProduceService;
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
 * Title : ProduceServiceImpl.java
 * Package : indi.zqc.warehouse.service.impl
 * Description : 生产服务实现
 * Create on : 2018/3/12 20:42
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
@Transactional
@Service
public class ProduceServiceImpl implements ProduceService {

    @Autowired
    private ProduceDao produceDao;

    @Override
    public int insertProduce(Produce produce) {
        produce.setProduceCode(newProduceCode());
        return produceDao.insertProduce(produce);
    }

    /**
     * 生产编号
     *
     * @return
     */
    private String newProduceCode() {
        return Constants.SC_PREFIX + DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
    }

    @Override
    public int deleteProduce(String produceCode) {
        return produceDao.deleteProduce(produceCode);
    }

    @Override
    public int updateProduce(Produce produce) {
        return produceDao.updateProduce(produce);
    }

    @Override
    public Produce selectProduce(String produceCode) {
        return produceDao.selectProduce(produceCode);
    }

    @Override
    public Page<Produce> selectProducePage(ProduceCondition condition) {
        PageHelper.startPage(condition.getPageNum(), condition.getNumPerPage());
        Page<Produce> produces = produceDao.selectProducePage(condition);
        return produces;
    }

    @Override
    public void exportProduce(ProduceCondition condition, HttpServletResponse response) {
        Page<Produce> produces = produceDao.selectProducePage(condition);
        OutputStream os = null;
        InputStream is = null;
        XSSFWorkbook workbook;
        XSSFSheet sheet;
        try {
            response.setContentType("application/vnd.ms-excel");
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(("生产表").getBytes(), "ISO8859-1") + ".xlsx");
            os = response.getOutputStream();
            is = new ClassPathResource(Constants.TEMPLATE_PRODUCE).getInputStream();
            workbook = new XSSFWorkbook(is);
            sheet = workbook.getSheetAt(0);
            XSSFRow styleRow = sheet.getRow(1);
            //生产表信息
            for (int i = 0; i < produces.size(); i++) {
                Produce produce = produces.get(i);
                XSSFRow row = sheet.createRow(i + 2);
                ExcelUtils.setCell(row, 0, styleRow.getCell(0).getCellStyle(), i + 1);
                ExcelUtils.setCell(row, 1, styleRow.getCell(1).getCellStyle(), produce.getProduceDateStr());
                ExcelUtils.setCell(row, 2, styleRow.getCell(2).getCellStyle(), produce.getProductionCode());
                ExcelUtils.setCell(row, 3, styleRow.getCell(3).getCellStyle(), produce.getProductionText());
                ExcelUtils.setCell(row, 4, styleRow.getCell(4).getCellStyle(), produce.getQuantity());
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
}

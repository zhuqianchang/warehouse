package indi.zqc.warehouse.util;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;

import java.util.Date;

/**
 * Title : ExcelUtils.java
 * Package : indi.zqc.warehouse.util
 * Description : EXCEL工具
 * Create on : 2018/2/24 15:17
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class ExcelUtils {

    //日期格式
    private static final String pattern = "yyyy-MM-dd HH:mm:ss";

    public static void setCell(XSSFRow row, int columnIndex, XSSFCellStyle cellStyle, String cellValue) {
        XSSFCell cell = row.createCell(columnIndex);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(cellValue);
    }

    public static void setCell(XSSFRow row, int columnIndex, XSSFCellStyle cellStyle, int cellValue) {
        setCell(row, columnIndex, cellStyle, String.valueOf(cellValue));
    }

    public static void setCell(XSSFRow row, int columnIndex, XSSFCellStyle cellStyle, Date cellValue) {
        setCell(row, columnIndex, cellStyle, DateFormatUtils.format(cellValue, "yyyy-MM-dd HH:mm:ss"));
    }

}

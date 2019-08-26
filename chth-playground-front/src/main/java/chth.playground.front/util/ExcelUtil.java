package chth.playground.front.util;

import chth.playground.common.DrugModel;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ExcelUtil {


    public static HSSFWorkbook createDrugList(String sheetName, String[] title, Map<String, List<DrugModel>> drugList, HSSFWorkbook wb) {

        HSSFCellStyle idStyle = wb.createCellStyle();
        idStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        Integer init = 1;

        //第一步创建一个sheet
        HSSFSheet sheet = wb.createSheet(sheetName);

        //第二步在sheet中添加表头
        HSSFRow row = sheet.createRow(0);

        //创建标题
        for (int i = 0; i < title.length; i++) {
            row.createCell(i).setCellValue(title[i]);
        }

        Set<String> ids = drugList.keySet();

        if (!ids.isEmpty()) {
            for (String id : ids) {
                List<DrugModel> list = drugList.get(id);
                CellRangeAddress region = new CellRangeAddress(init, init + list.size() - 1, 0, 0);
                sheet.addMergedRegion(region);
                for (DrugModel drugModel : list) {
                    row = sheet.createRow(init);
                    HSSFCell cell = row.createCell(0);
                    cell.setCellStyle(idStyle);
                    cell.setCellValue(id);
                    row.createCell(1).setCellValue(drugModel.getDrugCode());
                    row.createCell(2).setCellValue(drugModel.getDrugName());
                    row.createCell(3).setCellValue(drugModel.getDrugCount());
                    row.createCell(4).setCellValue(drugModel.getDrugPrice());
                    row.createCell(5).setCellValue(drugModel.getDrugSpec());
                    row.createCell(6).setCellValue(drugModel.getFormCode());
                    row.createCell(7).setCellValue(drugModel.getTotalDose());
                    row.createCell(8).setCellValue(drugModel.getRouteCode());
                    row.createCell(9).setCellValue(drugModel.getRate());
                    row.createCell(10).setCellValue(drugModel.getRateUnit());
                    row.createCell(11).setCellValue(drugModel.getDose());
                    row.createCell(12).setCellValue(drugModel.getDoseUnit());

                    init++;
                }
            }
        }

        return wb;
    }

    public static HSSFWorkbook getHSSFWorkbook(String sheetName, String[] title, String[][] values, HSSFWorkbook wb) {

        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        if (wb == null) {
            wb = new HSSFWorkbook();
        }

        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(0);

        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式

        //声明列对象
        HSSFCell cell = null;

        //创建标题
        for (int i = 0; i < title.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }

        if (values != null) {
            //创建内容
            for (int i = 0; i < values.length; i++) {
                row = sheet.createRow(i + 1);
                for (int j = 0; j < values[i].length; j++) {
                    //将内容按顺序赋给对应的列对象
                    row.createCell(j).setCellValue(values[i][j]);
                }
            }
        }

        return wb;
    }

    public static void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            fileName = new String(fileName.getBytes(), StandardCharsets.ISO_8859_1);
            response.setContentType("application/octet-stream;charset=ISO-8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

}

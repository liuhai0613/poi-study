package com.beijing.vagrancy.poistudy.service;

import com.beijing.vagrancy.poistudy.entity.ArticleEntity;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

/**
 * Created by liuhai on 2020/12/4 15:44
 */
@Service
public class PoiService1 {

    public void exportExcel(HttpServletResponse response){
        List<ArticleEntity> dataList = Arrays.asList(
                new ArticleEntity("001","法学期刊1","gnkh0001","gjqh00001","www.pkulaw.com1","2015-01-12","法学1","192.168.60.136","范围1","类型1"),
                new ArticleEntity("002","法学期刊2","gnkh0002","gjqh00002","www.pkulaw.com2","2015-01-15","法学2","192.168.60.137","范围2","类型2"),
                new ArticleEntity("003","法学期刊3","gnkh0003","gjqh00003","www.pkulaw.com3","2015-01-17","法学3","192.168.60.138","范围3","类型3")
        );

        SXSSFWorkbook workbook = new SXSSFWorkbook(100);
        //临时文件进行压缩，建议不要true，否则会影响导出时间
        workbook.setCompressTempFiles(false);
        SXSSFSheet sheet = workbook.createSheet("文章列表");
        CellStyle style = workbook.createCellStyle();
        // 创建第一行
        SXSSFRow row = sheet.createRow(0);
        //单元格对齐方式
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        // 给表头第一行创建单元格
        row.createCell(0).setCellValue("刊物ID");
        row.createCell(1).setCellValue("期刊名称");
        row.createCell(2).setCellValue("国内刊号");
        row.createCell(3).setCellValue("国际刊号");
        row.createCell(4).setCellValue("刊物官网");
        row.createCell(5).setCellValue("收录时间");
        row.createCell(6).setCellValue("期刊类型");
        row.createCell(7).setCellValue("数据库地址");
        row.createCell(8).setCellValue("收录具体范围");
        row.createCell(9).setCellValue("类型");
        //设置列宽
        for(int i = 0;i < row.getPhysicalNumberOfCells();i++){
            sheet.setColumnWidth(i,5000);
        }
        for (int i = 0; i < dataList.size(); i++) {
            row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(dataList.get(i).getId());
            row.createCell(1).setCellValue(dataList.get(i).getJournalName());
            row.createCell(2).setCellValue(dataList.get(i).getCN());
            row.createCell(3).setCellValue(dataList.get(i).getISSN());
            row.createCell(4).setCellValue(dataList.get(i).getOfficialWebsite());
            row.createCell(5).setCellValue(dataList.get(i).getRecordTime());
            row.createCell(6).setCellValue(dataList.get(i).getJournalType());
            row.createCell(7).setCellValue(dataList.get(i).getBaseDataAddress());
            row.createCell(8).setCellValue(dataList.get(i).getRecordRange());
            row.createCell(9).setCellValue(dataList.get(i).getType());
        }
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            response.setContentType("application/force-download");
            response.addHeader("Content-Disposition", "attachment;fileName=\""+ URLEncoder.encode("文章导出列表"+".xlsx","utf-8")+"\"");
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

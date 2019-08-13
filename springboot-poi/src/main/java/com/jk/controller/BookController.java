/**
 * Copyright (C), 2019, 公司
 * FileName: BookController
 * Author:   zgm
 * Date:     2019/8/8 16:43
 * Description: a
 * History:
 * zgm          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jk.controller;

import com.jk.model.Book;
import com.jk.service.BookService;
import com.jk.util.ExportExcel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈a〉
 *
 * @author zgm
 * @create 2019/8/8
 * @since 1.0.0
 */
@Controller

public class BookController {

    @Autowired
    private BookService bookService;
    @RequestMapping("queryBook")
    public String queryBook(Model model){
        List<Book> list=bookService.queryBook();
        model.addAttribute("list",list);
        return "show";
    }
    @RequestMapping("exportExcel")
    public void exportExcel(HttpServletResponse response){
        String title="1902b";
        String[] rowName={"编号","名称","价格","类型","日期"};
        List<Object[]> dataList = new ArrayList<>();
        List<Book> list=bookService.queryBook();
        for(Book book:list){
            Object[] obj = new Object[rowName.length];
            obj[0]=book.getId();
            obj[1]=book.getName();
            obj[2]=book.getPrice();
            obj[3]=book.getTypeid();
            SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
            String format = sdf.format(book.getTime());
            obj[4]=format;
            dataList.add(obj);
        }
        ExportExcel exportExcel =new ExportExcel(title,rowName,dataList,response);
        try {
            exportExcel.export();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("importExcel")
    public String importExcel(MultipartFile file, HttpServletResponse response){
        //获得上传文件上传的类型
        String contentType = file.getContentType();
        //上传文件的名称
        String fileName = file.getOriginalFilename();
        //获得文件的后缀名
        String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        //上传文件的新的路径
        //生成新的文件名称
        String filePath = "./src/main/resources/templates/fileupload/";
        //创建list集合接收excel中读取的数据
        List<Book> list =new ArrayList<Book>();
        try {
            uploadFile(file.getBytes(), filePath, fileName);
            SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
            //通过文件忽的WorkBook
            FileInputStream fileInputStream = new FileInputStream(filePath+fileName);
            Workbook workbook = WorkbookFactory.create(fileInputStream);
            //通过workbook对象获得sheet页 有可能不止一个sheet
            for(int i=0 ;i<workbook.getNumberOfSheets();i++){
                //获得里面的每一个sheet对象
                Sheet sheetAt = workbook.getSheetAt(i);
                //通过sheet对象获得每一行
                for(int j=3;j<sheetAt.getPhysicalNumberOfRows();j++){
                    //创建一个book对象接收excel的数据
                    Book book=new Book();
                    //获得每一行的数据
                    Row row = sheetAt.getRow(j);

                    //获得每一个单元格的数据
                    if(row.getCell(1)!=null && !"".equals(row.getCell(1))){
                        book.setName(this.getCellValue(row.getCell(1)));
                    }
                    if(row.getCell(2)!=null && !"".equals(row.getCell(2))){
                        book.setPrice(Integer.parseInt(this.getCellValue(row.getCell(2))));
                    }
                    if(row.getCell(3)!=null && !"".equals(row.getCell(3))){
                        book.setTypeid(Integer.parseInt(this.getCellValue(row.getCell(3))));
                    }
                    if(row.getCell(4)!=null && !"".equals(row.getCell(4))){
                        book.setTime(sdf.parse(this.getCellValue(row.getCell(4))));
                    }
                    /*if(row.getCell(5)!=null && !"".equals(row.getCell(5))){
                        String cellValue = this.getCellValue(row.getCell(4));
                        if("小说".equals(cellValue)){
                            book.setBooktypeid(1);
                        }else{
                            book.setBooktypeid(2);
                        }

                    }*/
                    list.add(book);
                }
            }
            bookService.addBook(list);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "show";
    }
    //上传文件的方法
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }
    //判断从Excel文件中解析出来数据的格式
    private static String getCellValue(Cell cell){
        String value = null;
        //简单的查检列类型
        switch(cell.getCellType())
        {
            case Cell.CELL_TYPE_STRING://字符串
                value = cell.getRichStringCellValue().getString();
                break;
            case Cell.CELL_TYPE_NUMERIC://数字
                long dd = (long)cell.getNumericCellValue();
                value = dd+"";
                break;
            case Cell.CELL_TYPE_BLANK:
                value = "";
                break;
            case Cell.CELL_TYPE_FORMULA:
                value = String.valueOf(cell.getCellFormula());
                break;
            case Cell.CELL_TYPE_BOOLEAN://boolean型值
                value = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_ERROR:
                value = String.valueOf(cell.getErrorCellValue());
                break;
            default:
                break;
        }
        return value;
    }
    //判断从Excel文件中解析出来数据的格式
    private static String getCellValue(XSSFCell cell){
        String value = null;
        //简单的查检列类型
        switch(cell.getCellType())
        {
            case HSSFCell.CELL_TYPE_STRING://字符串
                value = cell.getRichStringCellValue().getString();
                break;
            case HSSFCell.CELL_TYPE_NUMERIC://数字
                long dd = (long)cell.getNumericCellValue();
                value = dd+"";
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                value = "";
                break;
            case HSSFCell.CELL_TYPE_FORMULA:
                value = String.valueOf(cell.getCellFormula());
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN://boolean型值
                value = String.valueOf(cell.getBooleanCellValue());
                break;
            case HSSFCell.CELL_TYPE_ERROR:
                value = String.valueOf(cell.getErrorCellValue());
                break;
            default:
                break;
        }
        return value;
    }
}

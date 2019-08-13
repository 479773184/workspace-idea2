/**
 * Copyright (C), 2019, 公司
 * FileName: BookController
 * Author:   zgm
 * Date:     2019/8/6 19:56
 * Description: a
 * History:
 * zgm          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jk.controller;

import com.jk.model.Book;
import com.jk.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈a〉
 *
 * @author Lenovo
 * @create 2019/8/6
 * @since 1.0.0
 */
@RequestMapping("book")
@Controller
public class BookController {

    @Autowired
    private BookService bookService;
    @RequestMapping("query")
    public String query(Model model){
        List<Book> list =   bookService.query();
        model.addAttribute("list",list);
        return  "show";
    }
    @RequestMapping("add")
    @ResponseBody
    public void addBook(){
        Book book=new Book();
        book.setPrice(12);
        book.setTime(new Date());
        book.setName("数据");
        book.setTypeid(1);
        int total =100000%1000==0?100:101;

        for (int i=0;i<100;i++){
            List<Book> list =new ArrayList<>(1000);
            System.out.println("线程名称："+Thread.currentThread().getName()+"i="+i);
            for (int j=0;j<1000;j++){
                list.add(book);
            }
            bookService.addBook(list);
        }

    }


}

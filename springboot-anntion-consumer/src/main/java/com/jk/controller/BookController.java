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

import com.alibaba.dubbo.config.annotation.Reference;
import com.jk.model.Book;
import com.jk.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @Reference
    private BookService bookService;

    @RequestMapping("queryBook")
    @ResponseBody
    public List<Book> queryBook() {
        List<Book> list = bookService.queryBook();

        return list;
    }

    @RequestMapping("toshow")

    public String toshow() {
        return "show";
    }

    @RequestMapping("delBookById")
    @ResponseBody
    public Boolean delBookById(Integer[] ids) {
        try {
            bookService.delBookById(ids);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("saveBtn")
    @ResponseBody
    public Boolean saveBtn(Book book) {
        try {
            if (book.getId() != null) {
                bookService.editBtn(book);
            } else {
                bookService.saveBtn(book);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("findById")
    @ResponseBody
    public Book findById(Integer id) {

        return bookService.findById(id);
    }

}

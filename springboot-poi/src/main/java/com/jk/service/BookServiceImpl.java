/**
 * Copyright (C), 2019, 公司
 * FileName: BookServiceImpl
 * Author:   zgm
 * Date:     2019/8/8 16:48
 * Description: a
 * History:
 * zgm          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jk.service;

import com.jk.dao.BookMapper;

import com.jk.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈a〉
 *
 * @author zgm
 * @create 2019/8/8
 * @since 1.0.0
 */
@Service
public class BookServiceImpl implements BookService {


    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<Book> queryBook() {

        return bookMapper.queryBook();
    }

    @Override
    public void addBook(List<Book> list) {
        bookMapper.addBook(list);
    }
}

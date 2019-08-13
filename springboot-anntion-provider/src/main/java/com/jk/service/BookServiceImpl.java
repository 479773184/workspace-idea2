/**
 * Copyright (C), 2019, 公司
 * FileName: BookServiceImpl
 * Author:   zgm
 * Date:     2019/8/6 20:14
 * Description: a
 * History:
 * zgm          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jk.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jk.dao.BookMapper;
import com.jk.model.Book;
import org.springframework.beans.factory.annotation.Autowired;



import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈a〉
 *
 * @author Lenovo
 * @create 2019/8/6
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
    public void delBookById(Integer[] ids) {

        bookMapper.delBookById(ids);
    }

    @Override
    public void saveBtn(Book book) {
        bookMapper.saveBtn(book);
    }

    @Override
    public Book findById(Integer id) {
        return bookMapper.findById(id);
    }

    @Override
    public void editBtn(Book book) {
        bookMapper.editBtn(book);
    }
}

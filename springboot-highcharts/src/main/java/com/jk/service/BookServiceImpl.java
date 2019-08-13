/**
 * Copyright (C), 2019, 公司
 * FileName: BookServiceImpl
 * Author:   zgm
 * Date:     2019/8/12 19:53
 * Description: a
 * History:
 * zgm          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jk.service;

import com.jk.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈a〉
 *
 * @author zgm
 * @create 2019/8/12
 * @since 1.0.0
 */
@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookDao bookDao;
    @Override
    public List<Map<String, Object>> queryBook() {
        return bookDao.queryBook();
    }

    @Override
    public List<Map<String, Object>> queryMM() {
        return bookDao.queryMM();
    }

    @Override
    public List<Map<String, Object>> queryZhu() {
        return bookDao.queryZhu();
    }
}

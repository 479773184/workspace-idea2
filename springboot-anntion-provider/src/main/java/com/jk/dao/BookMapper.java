package com.jk.dao;

import com.jk.model.Book;

import java.util.List;

public interface BookMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);

    List<Book> queryBook();

    void delBookById(Integer[] ids);

    void saveBtn(Book book);

    Book findById(Integer id);

    void editBtn(Book book);
}
package com.jk.dao;

import com.jk.model.Book;

import java.util.List;

public interface BookMapper {

    List<Book> queryBook();

    void addBook(List<Book> list);
}
package com.jk.dao;

import com.jk.model.Book;

import java.util.List;

public interface BookDao {
    List<Book> query();

    void addBook(List<Book> list);
}
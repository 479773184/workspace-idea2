package com.jk.service;

import com.jk.model.Book;

import java.util.List;

public interface BookService {


    List<Book> queryBook();

    void addBook(List<Book> list);
}
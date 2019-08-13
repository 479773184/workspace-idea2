package com.jk.service;

import com.jk.model.Book;

import java.util.List;

public interface BookService {


    List<Book> queryBook();

    void delBookById(Integer[] ids);

    void saveBtn(Book book);

    Book findById(Integer id);

    void editBtn(Book book);
}
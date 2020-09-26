package com.demo.dao;

import com.demo.entity.Book;

public interface BookDao {
    void addBook(Book book);
    void updateBook(Book book);
    void deleteBook(Integer id);
}

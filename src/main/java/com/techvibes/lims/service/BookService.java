package com.techvibes.lims.service;

import com.techvibes.lims.model.Book;

import java.util.List;

public interface BookService {
    public List<Book> findAllBooks();

    public List<Book> searchBooks(String keyword);

    public Book findBookById(Long id);

    public void createBook(Book book);

    public void updateBook(Book book);

    public void deleteBook(Long id);
}

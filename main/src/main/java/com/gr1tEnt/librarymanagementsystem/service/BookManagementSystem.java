package com.gr1tEnt.librarymanagementsystem.service;

import com.gr1tEnt.librarymanagementsystem.model.Book;

import java.util.Map;

public interface BookManagementSystem {
    void addNewBook();
    void removeBook();
    void updateBook();
    void updateStatus();
    void trackCopies();
    void printAllBooks(Map<Long, Book> books);
}

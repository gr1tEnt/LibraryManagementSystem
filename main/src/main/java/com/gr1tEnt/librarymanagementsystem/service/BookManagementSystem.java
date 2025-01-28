package com.gr1tEnt.librarymanagementsystem.service;

public interface BookManagementSystem {
    void addNewBook();
    void removeBook();
    void updateBook();
    void updateStatus();
    void trackCopies();
    void printAllBooks();
}

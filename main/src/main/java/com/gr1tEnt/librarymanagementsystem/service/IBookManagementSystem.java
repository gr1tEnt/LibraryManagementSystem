package com.gr1tEnt.librarymanagementsystem.service;

public interface IBookManagementSystem {
    void addNewBook();
    void removeBook();
    void updateBook();
    void updateStatus();
    void trackCopies();
    void updateIsbn();
    void updateTitle();
    void updateAuthors();
    void updatePublisher();
    void updateYear();
}

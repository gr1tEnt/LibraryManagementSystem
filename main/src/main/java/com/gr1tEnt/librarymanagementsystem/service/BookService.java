package com.gr1tEnt.librarymanagementsystem.service;

import com.gr1tEnt.librarymanagementsystem.model.Book;
import com.gr1tEnt.librarymanagementsystem.model.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BookService {
    private static final List<Book> books = new ArrayList<>();

    // I'm not sure if I should give users the ability to set IDs (this should be implemented through the database)
    public static Book addBook(Long id, String isbn, String title, Set<String> authors, String publisher, int publicationYear, String category) {
        Book book = new Book(id, isbn, title, authors, publisher, publicationYear, category);
        books.add(book);
        return book;
    }

    public static void removeBook(int bookId) {
        if (bookId >= 0 && bookId <=  books.size()) {
            books.remove(bookId);
        } else {
            System.out.println("Invalid book's ID. Please try again");
        }
    }

    public static void setStatus(int bookId, Status status) {
        if (bookId >= 0 && bookId <= books.size()) {
            Book book = books.get(bookId);
            book.setStatus(status);
            System.out.println("The book has changed its status" + book);
        } else {
            System.out.println("Invalid book's ID. Please try again");
        }
    }
}

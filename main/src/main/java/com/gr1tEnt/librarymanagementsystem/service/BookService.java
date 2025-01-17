package com.gr1tEnt.librarymanagementsystem.service;

import com.gr1tEnt.librarymanagementsystem.model.Book;
import com.gr1tEnt.librarymanagementsystem.model.Status;

import java.util.*;

public class BookService {
    private static final Map<Long, Book> books = new HashMap<>();

    // I'm not sure if I should give users the ability to set IDs (this should be implemented through the database)
    public static Book addBook(Long id, String isbn, String title, Set<String> authors, String publisher, int publicationYear, String category) {
        Book book = new Book(id, isbn, title, authors, publisher, publicationYear, category);
        books.put(id, book);
        return book;
    }

    public static boolean removeBook(Long id) {
        if (books.containsKey(id)) {
            Book removedBook = books.remove(id);
            System.out.println("Book removed " + removedBook);
            return true;
        } else {
            System.out.println("Invalid book's ID. Please try again");
            return false;
        }
    }

    public static Map<Long, Book> getAllBooks() {
        return books;
    }

    public static void updateBookStatus(Long id, Status newStatus) {
        Book book = books.get(id);
        if (book != null) {
            book.setStatus(newStatus);
            System.out.println("The status of the book has been updated: " + book);
        } else {
            System.out.println("Invalid book's ID. Please try again.");
        }
    }
}

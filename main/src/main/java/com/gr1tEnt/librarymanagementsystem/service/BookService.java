package com.gr1tEnt.librarymanagementsystem.service;

import com.gr1tEnt.librarymanagementsystem.model.Book;
import com.gr1tEnt.librarymanagementsystem.model.Category;
import com.gr1tEnt.librarymanagementsystem.model.Status;

import java.util.*;

public class BookService {
    private static final Map<Long, Book> books = new HashMap<>();

    public static Book addBook(Long id, String isbn, String title, String publisher) {
        Set<String> authors = AuthorsService.getValidAuthors();
        int publicationYear = PublicationYearService.getValidYear();
        Category category = CategoryService.getValidCategory();

        if (books.containsKey(id)) {
            throw new IllegalArgumentException("Book with ID " + id + " already exists");
        }

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

    public static void updateBook(Long id, Book updatedBook) {
        if (books.containsKey(id)) {
            books.replace(id, updatedBook);
            System.out.println("Book has been updated: " + updatedBook);
        } else {
            System.out.println("Book with ID " + id + " not found");
        }
    }

    public static void trackBookCopies(Long bookId, int quantityOfCopies) {
        Book book = books.get(bookId);

        if (book != null) {
            book.setNumberOfCopies(quantityOfCopies);
            System.out.println("Number of copies has benn updated: " + book);
        } else {
            System.out.println("Book with ID " + bookId + " not found.");
        }

    }

    public static boolean bookExists(Long id) {
        return books.containsKey(id);
    }
}

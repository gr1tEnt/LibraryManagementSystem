package com.gr1tEnt.librarymanagementsystem.service;

import com.gr1tEnt.librarymanagementsystem.model.Book;
import com.gr1tEnt.librarymanagementsystem.model.Category;
import com.gr1tEnt.librarymanagementsystem.model.Status;
import com.gr1tEnt.librarymanagementsystem.utils.InputValidator;

import java.util.Scanner;
import java.util.Set;

public class BookManagementSystem implements IBookManagementSystem {
    public final Scanner scanner;
    private final BookService bookService;
    private final InputValidator inputValidator;

    public BookManagementSystem(BookService bookService, Scanner scanner, InputValidator inputValidator) {
        this.bookService = bookService;
        this.scanner = scanner;
        this.inputValidator = inputValidator;
    }

    @Override
    public void addNewBook() {

        System.out.println("Enter ISBN: ");
        String isbn = scanner.nextLine();

        System.out.println("Enter title: ");
        String title = scanner.nextLine();

        Set<String> authors = inputValidator.getValidAuthors();

        System.out.println("Enter publisher: ");
        String publisher = scanner.nextLine();

        int publicationYear = inputValidator.getValidYear();

        Category category = inputValidator.getValidCategory();

        int numberOfCopies = inputValidator.getValidInt("Enter numberOfCopies: ");

        System.out.println("Enter shelfLocation: ");
        String shelfLocation = scanner.nextLine();

        Status status = inputValidator.getValidStatus("Enter status: ");

        Book book = new Book(isbn, title, authors, publisher, publicationYear, category, numberOfCopies, shelfLocation, status);
        BookService.addBook(book);
    }


    @Override
    public void removeBook() {
        long bookId = inputValidator.getValidId();
        BookService.removeBook(bookId);
    }

    // I'll add ability to update the individual properties
    @Override
    public void updateBook() {
        long bookId = inputValidator.getValidId();

        System.out.println("Enter new isbn");
        String newIsbn = scanner.nextLine();

        System.out.println("Enter new title" );
        String newTitle = scanner.nextLine();

        System.out.println("Enter new publisher");
        String newPublisher = scanner.nextLine();

//        BookService.updateBook(bookId, newIsbn, newTitle, newPublisher);
    }

    @Override
    public void updateStatus() {
        long bookId  = inputValidator.getValidId();

        Status newStatus = inputValidator.getValidStatus("Enter new status: ");

        BookService.updateBookStatus(bookId, newStatus);
    }

    @Override
    public void updateIsbn() {
        Long bookId = inputValidator.getValidId();

        System.out.println("Enter new ISBN: ");
        String newIsbn = scanner.nextLine();

        BookService.updateIsbn(bookId, newIsbn);
    }


    // I'll add ability to see the quantity of books, using title, authors etc.
    @Override
    public void trackCopies() {
        long bookId = inputValidator.getValidId();
        BookService.trackBookCopies(bookId);
    }

}


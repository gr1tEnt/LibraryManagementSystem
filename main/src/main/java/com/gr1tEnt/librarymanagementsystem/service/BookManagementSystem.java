package com.gr1tEnt.librarymanagementsystem.service;

import com.gr1tEnt.librarymanagementsystem.model.*;
import com.gr1tEnt.librarymanagementsystem.utils.InputValidator;

import java.util.Scanner;
import java.util.Set;

public class BookManagementSystem implements IBookManagementSystem, IUpdateBookManagementSystem {
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

        ShelfLocation shelfLocation = inputValidator.getValidShelfLocation();

        Status status = inputValidator.getValidStatus("Enter status: ");

        Book book = new Book(isbn, title, authors, publisher, publicationYear, category, numberOfCopies, shelfLocation, status);
        BookService.addBook(book);
    }


    @Override
    public void removeBook() {
        long bookId = inputValidator.getValidId();
        BookService.removeBook(bookId);
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

    @Override
    public void updateTitle() {
        Long bookId = inputValidator.getValidId();

        System.out.println("Enter new title: ");
        String newTitle = scanner.nextLine();

        BookService.updateTitle(bookId, newTitle);
    }

    @Override
    public void updateAuthors() {
        Long bookId = inputValidator.getValidId();

        Set<String> newAuthors = inputValidator.getValidAuthors();

        BookService.updateAuthors(bookId,newAuthors);
    }

    @Override
    public void updatePublisher() {
        Long bookId = inputValidator.getValidId();

        System.out.println("Enter new publisher: ");
        String newPublisher = scanner.nextLine();

        BookService.updatePublisher(bookId, newPublisher);
    }

    @Override
    public void updateYear() {
        Long bookId = inputValidator.getValidId();
        int newYear = inputValidator.getValidYear();

        BookService.updateYear(bookId, newYear);
    }
    @Override
    public void updateCategory() {
        Long bookId = inputValidator.getValidId();
        Category newCategory = inputValidator.getValidCategory();

        BookService.updateCategory(bookId, newCategory);
    }

    @Override
    public void updateNumberOfCopies() {
        Long bookId = inputValidator.getValidId();
        int newNumberOfCopies = inputValidator.getValidInt("Enter new number of copies: ");

        BookService.updateNumberOfCopies(bookId, newNumberOfCopies);
    }

    @Override
    public void updateShelfLocation() {
        Long bookId = inputValidator.getValidId();
        ShelfLocation newShelflocation = inputValidator.getValidShelfLocation();

        BookService.updateShelfLocation(bookId, newShelflocation);
    }


    // I'll add ability to see the quantity of books, using title, authors etc.
    @Override
    public void trackCopies() {
        long bookId = inputValidator.getValidId();
        BookService.trackBookCopies(bookId);
    }

}


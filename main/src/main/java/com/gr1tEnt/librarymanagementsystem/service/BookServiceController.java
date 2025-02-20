package com.gr1tEnt.librarymanagementsystem.service;

import com.gr1tEnt.librarymanagementsystem.model.*;
import com.gr1tEnt.librarymanagementsystem.utils.InputValidator;

import java.util.Scanner;
import java.util.Set;
import java.util.UUID;

public class BookServiceController implements IBookServiceController, IUpdateBookServiceController {
    public final Scanner scanner;
    private final BookService bookService;
    private final InputValidator inputValidator;

    public BookServiceController(BookService bookService, Scanner scanner, InputValidator inputValidator) {
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
        UUID bookId = inputValidator.getValidId();
        BookService.removeBook(bookId);
    }

    @Override
    public void updateStatus() {
        UUID bookId  = inputValidator.getValidId();

        Status newStatus = inputValidator.getValidStatus("Enter new status: ");

        BookService.updateBookStatus(bookId, newStatus);
    }

    @Override
    public void updateIsbn() {
        UUID bookId = inputValidator.getValidId();

        System.out.println("Enter new ISBN: ");
        String newIsbn = scanner.nextLine();

        BookService.updateIsbn(bookId, newIsbn);
    }

    @Override
    public void updateTitle() {
        UUID bookId = inputValidator.getValidId();

        System.out.println("Enter new title: ");
        String newTitle = scanner.nextLine();

        BookService.updateTitle(bookId, newTitle);
    }

    @Override
    public void updateAuthors() {
        UUID bookId = inputValidator.getValidId();

        Set<String> newAuthors = inputValidator.getValidAuthors();

        BookService.updateAuthors(bookId,newAuthors);
    }

    @Override
    public void updatePublisher() {
        UUID bookId = inputValidator.getValidId();

        System.out.println("Enter new publisher: ");
        String newPublisher = scanner.nextLine();

        BookService.updatePublisher(bookId, newPublisher);
    }

    @Override
    public void updateYear() {
        UUID bookId = inputValidator.getValidId();
        int newYear = inputValidator.getValidYear();

        BookService.updateYear(bookId, newYear);
    }
    @Override
    public void updateCategory() {
        UUID bookId = inputValidator.getValidId();
        Category newCategory = inputValidator.getValidCategory();

        BookService.updateCategory(bookId, newCategory);
    }

    @Override
    public void updateNumberOfCopies() {
        UUID bookId = inputValidator.getValidId();
        int newNumberOfCopies = inputValidator.getValidInt("Enter new number of copies: ");

        BookService.updateNumberOfCopies(bookId, newNumberOfCopies);
    }

    @Override
    public void updateShelfLocation() {
        UUID bookId = inputValidator.getValidId();
        ShelfLocation newShelflocation = inputValidator.getValidShelfLocation();

        BookService.updateShelfLocation(bookId, newShelflocation);
    }


    // I'll add ability to see the quantity of books, using title, authors etc.
    @Override
    public void trackCopies() {
        UUID bookId = inputValidator.getValidId();
        BookService.trackBookCopies(bookId);
    }

}


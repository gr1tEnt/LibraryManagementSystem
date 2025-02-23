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

        String isbn = inputValidator.getValidIsbnOptions();

        System.out.println("Enter title: ");
        String title = scanner.nextLine();

        Set<String> authors = inputValidator.getValidAuthorsOptions();

        System.out.println("Enter publisher: ");
        String publisher = scanner.nextLine();

        int publicationYear = inputValidator.getValidYearOptions();

        Category category = inputValidator.getValidCategoryOptions();

        int numberOfCopies = inputValidator.getValidIntOptions("Enter number of copies: ");

        ShelfLocation shelfLocation = inputValidator.getValidShelfLocationOptions();

        Status status = inputValidator.getValidStatusOptions("Enter status: ");

        Book book = new Book(isbn, title, authors, publisher, publicationYear, category, numberOfCopies, shelfLocation, status);

        boolean isAdded = bookService.addBook(book);

        if (isAdded) {
            System.out.println("Book added successfully.");
        } else {
            System.out.println("failed to add the book.");
        }
    }


    @Override
    public void removeBook() {
        UUID bookId = inputValidator.getValidId();
        boolean isRemoved = bookService.removeBook(bookId);

        if (isRemoved) {
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("No book found with such ID.");
        }
    }

    @Override
    public void updateStatus() {
        UUID bookId = inputValidator.getValidId();

        Status newStatus = inputValidator.getValidStatusOptions("Enter new status: ");

        bookService.updateBookStatus(bookId, newStatus);
    }

    @Override
    public void updateIsbn() {
        UUID bookId = inputValidator.getValidId();

        String newIsbn = inputValidator.getValidIsbnOptions();

        bookService.updateIsbn(bookId, newIsbn);
    }

    @Override
    public void updateTitle() {
        UUID bookId = inputValidator.getValidId();

        System.out.println("Enter new title: ");
        String newTitle = scanner.nextLine();

        bookService.updateTitle(bookId, newTitle);
    }

    @Override
    public void updateAuthors() {
        UUID bookId = inputValidator.getValidId();

        Set<String> newAuthors = inputValidator.getValidAuthorsOptions();

        bookService.updateAuthors(bookId, newAuthors);
    }

    @Override
    public void updatePublisher() {
        UUID bookId = inputValidator.getValidId();

        System.out.println("Enter new publisher: ");
        String newPublisher = scanner.nextLine();

        bookService.updatePublisher(bookId, newPublisher);
    }

    @Override
    public void updateYear() {
        UUID bookId = inputValidator.getValidId();
        int newYear = inputValidator.getValidYearOptions();

        bookService.updateYear(bookId, newYear);
    }

    @Override
    public void updateCategory() {
        UUID bookId = inputValidator.getValidId();
        Category newCategory = inputValidator.getValidCategoryOptions();

        bookService.updateCategory(bookId, newCategory);
    }

    @Override
    public void updateNumberOfCopies() {
        UUID bookId = inputValidator.getValidId();
        int newNumberOfCopies = inputValidator.getValidIntOptions("Enter new number of copies: ");

        bookService.updateNumberOfCopies(bookId, newNumberOfCopies);
    }

    @Override
    public void updateShelfLocation() {
        UUID bookId = inputValidator.getValidId();
        ShelfLocation newShelflocation = inputValidator.getValidShelfLocationOptions();

        bookService.updateShelfLocation(bookId, newShelflocation);
    }


    // I'll add ability to see the quantity of books, using title, authors etc.
    @Override
    public void trackCopies() {
        UUID bookId = inputValidator.getValidId();

        bookService.trackBookCopies(bookId);
    }

}


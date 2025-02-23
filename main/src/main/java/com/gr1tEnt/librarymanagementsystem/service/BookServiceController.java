package com.gr1tEnt.librarymanagementsystem.service;

import com.gr1tEnt.librarymanagementsystem.model.*;
import com.gr1tEnt.librarymanagementsystem.utils.InputValidator;

import java.util.Scanner;
import java.util.Set;
import java.util.UUID;

public class BookServiceController implements IBookServiceController {
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

    public void updateBook() {
        UUID bookId = inputValidator.getValidId();
        String bookColumn = inputValidator.getValidBookColumnOptions().toString().toLowerCase();

        System.out.println("Enter new value: ");
        Object newValue = null;

        switch (bookColumn) {
            case "isbn" -> newValue = inputValidator.getValidIsbnOptions();
            case "title" -> newValue = scanner.nextLine();
            case "authors" -> newValue = inputValidator.getValidAuthorsOptions();
            case "publisher" -> newValue = scanner.nextLine();
            case "publication_year" -> newValue = inputValidator.getValidYearOptions();
            case "category" -> newValue = inputValidator.getValidCategoryOptions();
            case "number_of_copies" -> newValue = inputValidator.getValidIntOptions("Enter number of copies: ");
            case "shelf_location" -> newValue = inputValidator.getValidShelfLocationOptions();
            case "status" -> newValue = inputValidator.getValidStatusOptions("Enter new status: ");
            default -> System.out.println("Incorrect choice.");
        }

        boolean isUpdated = bookService.updateBookField(bookId, bookColumn, newValue);
        if (isUpdated) {
            System.out.println("Book updated successfully.");
        } else {
            System.out.println("No book found with such ID.");
        }
    }

    // I'll add ability to see the quantity of books, using title, authors etc.
    @Override
    public void trackCopies() {
        UUID bookId = inputValidator.getValidId();

        bookService.trackBookCopies(bookId);
    }

}


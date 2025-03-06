package com.gr1tEnt.librarymanagementsystem.service;

import com.gr1tEnt.librarymanagementsystem.model.*;
import com.gr1tEnt.librarymanagementsystem.utils.InputValidator;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.UUID;

public class BookManager implements BookManagerInterface {
    public final Scanner scanner;
    private final BookService bookService;
    private final InputValidator inputValidator;

    public BookManager(BookService bookService, Scanner scanner, InputValidator inputValidator) {
        this.bookService = bookService;
        this.scanner = scanner;
        this.inputValidator = inputValidator;
    }

    @Override
    public void addNewBook() {
        String isbn = inputValidator.getValidIsbnOptions();
        String title = inputValidator.getValidStringOptions("Enter title: ");
        Set<String> authors = inputValidator.getValidAuthorsOptions();
        String publisher = inputValidator.getValidStringOptions("Enter publisher: ");
        int publicationYear = inputValidator.getValidYearOptions();
        Category category = inputValidator.getValidCategoryOptions();
        int numberOfCopies = inputValidator.getValidIntOptions();
        ShelfLocation shelfLocation = inputValidator.getValidShelfLocationOptions();
        Status status = inputValidator.getValidStatusOptions();

        Book book = new Book(isbn, title, authors, publisher, publicationYear, category, numberOfCopies, shelfLocation, status);

        boolean isAdded = bookService.addBook(book);
        if (isAdded) {
            System.out.println("Book added successfully.");
        } else {
            System.out.println("Failed to add the book. Please check the input values.");
        }
    }

    @Override
    public void removeBookById() {
        UUID bookId = inputValidator.getValidId();
        boolean isRemoved = bookService.removeBook(bookId);

        if (isRemoved) {
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("No book found with such ID.");
        }
    }

    public void updateBookField() {
        UUID bookId = inputValidator.getValidId();
        String bookColumn = inputValidator.getValidBookColumnOptions().toString().toLowerCase();

        System.out.println("Enter new value: ");
        Object newValue = null;

        switch (bookColumn) {
            case "isbn" -> newValue = inputValidator.getValidIsbnOptions();
            case "title" -> newValue = inputValidator.getValidStringOptions("Enter new title: ");
            case "authors" -> newValue = inputValidator.getValidAuthorsOptions();
            case "publisher" -> newValue = inputValidator.getValidStringOptions("Enter new publisher: ");
            case "publication_year" -> newValue = inputValidator.getValidYearOptions();
            case "category" -> newValue = inputValidator.getValidCategoryOptions();
            case "number_of_copies" -> newValue = inputValidator.getValidIntOptions();
            case "shelf_location" -> newValue = inputValidator.getValidShelfLocationOptions();
            case "status" -> newValue = inputValidator.getValidStatusOptions();
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

    public Set<String> getValidAuthors() {
        Set<String> authors = new HashSet<>();
        while (true) {
            System.out.println("Enter author (type 'done' to finish):");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("done")) {
                break;
            }
            if (!input.isEmpty()) {
                authors.add(input);
            } else {
                System.out.println("Author name cannot be empty. Please try again.");
            }
        }
        return authors;
    }

}


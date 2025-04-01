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
        String isbn = getValidIsbnInput();
        String title = getValidStringInput("Enter title: ");
        Set<String> authors = getValidAuthors();
        String publisher =  getValidStringInput("Enter publisher: ");
        int publicationYear = getValidYearInput();
        Category category = getValidCategoryInput();
        int numberOfCopies = getValidIntInput("Enter number of copies: ");
        ShelfLocation shelfLocation = getValidShelfLocationInput();
        Status status = getValidStatusInput();

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
        UUID bookId = getValidIdInput();
        boolean isRemoved = bookService.removeBook(bookId);

        if (isRemoved) {
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("No book found with such ID.");
        }
    }

    public void updateBookField() {
        UUID bookId = getValidIdInput();
        String bookColumn = getValidBookColumnInput();

        System.out.println("Enter new value: ");
        Object newValue = null;

        switch (bookColumn) {
            case "isbn" -> newValue = getValidIsbnInput();
            case "title" -> newValue = getValidStringInput("Enter new title: ");
            case "authors" -> newValue = getValidAuthors();
            case "publisher" -> newValue = getValidStringInput("Enter new publisher: ");
            case "publication_year" -> newValue = getValidYearInput();
            case "category" -> newValue = getValidCategoryInput();
            case "number_of_copies" -> newValue = getValidIntInput("Enter new number of copies: ");
            case "shelf_location" -> newValue = getValidShelfLocationInput();
            case "status" -> newValue = getValidStatusInput();
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
        UUID bookId = getValidIdInput();
        bookService.trackBookCopies(bookId);
    }

    private String getValidStringInput(String message) {
        while (true) {
            System.out.println(message);
            String input = scanner.nextLine();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("This value cannot be empty.");
        }
    }

    private int getValidIntInput(String message) {
        while (true) {
            System.out.println(message);
            String input = scanner.nextLine().trim();
            if (inputValidator.isValidInt(input)) {
                return Integer.parseInt(input);
            }
            System.out.println("Invalid number. Please try again.");
        }
    }

    private int getValidYearInput() {
        while (true) {
            System.out.println("Enter publication year: ");
            String input = scanner.nextLine().trim();
            if (inputValidator.isValidInt(input)) {
                int year = Integer.parseInt(input);
                if (inputValidator.isValidYear(year)) {
                    return year;
                }
                System.out.println("Invalid year. Please enter a year between 1450 and the current year.");
            } else {
                System.out.println("Invalid input. Please enter a valid year.");
            }
        }
    }

    private Category getValidCategoryInput() {
        while (true) {
            System.out.println("Enter category: ");
            String input = scanner.nextLine().trim();
            if (inputValidator.isValidCategory(input)) {
                return Category.valueOf(input.toUpperCase());
            }
            System.out.println("Invalid category. Please try again.");
        }
    }

    private ShelfLocation getValidShelfLocationInput() {
        while (true) {
            System.out.println("Enter shelf location: ");
            String input = scanner.nextLine().trim();
            if (inputValidator.isValidShelfLocation(input)) {
                return ShelfLocation.valueOf(input.toUpperCase());
            }
            System.out.println("Invalid location. Please try again.");
        }
    }

    private Status getValidStatusInput() {
        while (true) {
            System.out.println("Enter status: ");
            String input = scanner.nextLine().trim();
            if (inputValidator.isValidStatus(input)) {
                return Status.valueOf(input.toUpperCase());
            }
            System.out.println("Invalid status. Please try again.");
        }
    }

    private UUID getValidIdInput() {
        while (true) {
            System.out.println("Enter book's ID: ");
            String input = scanner.nextLine().trim();
            if (inputValidator.isValidUuid(input)) {
                return UUID.fromString(input);
            }
            System.out.println("Invalid UUID. Please try again.");
        }
    }

    private String getValidIsbnInput() {
        while (true) {
            System.out.println("Enter ISBN (10 or 13 digits): ");
            String input = scanner.nextLine().trim();
            if (inputValidator.isValidIsbn(input)) {
                return input;
            } else {
                System.out.println("Invalid ISBN. Please enter a valid ISBN (10 or 13 digits).");
            }
        }
    }

    private String getValidBookColumnInput() {
        while (true) {
            System.out.println("Enter book column to update: ");
            String input = scanner.nextLine().trim();
            if (inputValidator.isValidBookColumn(input)) {
                return input;
            }
            System.out.println("Invalid column. Please try again.");
        }
    }

    private Set<String> getValidAuthors() {
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


package com.gr1tEnt.librarymanagementsystem.utils;

import com.gr1tEnt.librarymanagementsystem.model.BookColumn;
import com.gr1tEnt.librarymanagementsystem.model.Category;
import com.gr1tEnt.librarymanagementsystem.model.ShelfLocation;
import com.gr1tEnt.librarymanagementsystem.model.Status;
import com.gr1tEnt.librarymanagementsystem.service.BookService;

import java.time.Year;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.UUID;

public class InputValidator {
    private final Scanner scanner;
    private final BookService bookService;

    public InputValidator(Scanner scanner, BookService bookService) {
        this.scanner = scanner;
        this.bookService = bookService;
    }

    public UUID getValidId() {
        UUID value = null;
        while (value == null) {
            System.out.println("Enter book's ID: ");
            String input = scanner.nextLine();

            try {
                value = UUID.fromString(input);

                if (!bookService.isBookExists(value)) {
                    System.out.println("Book with ID " + value + " doesn't exist. Please enter a valid ID.");
                    value = null;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Please enter a valid UUID.");
            }
        }
        return value;
    }

    // I'll improve this method later, cuz it requires pattern knowledge
    public String getValidIsbnOptions() {
        while (true) {
            System.out.println("Enter ISBN (10-13 digits: )");
            String input = scanner.nextLine().trim();

            if (isValidIsbn(input)) {
                return input;
            } else {
                System.out.println("Invalid ISBN. Please try again.");
            }
        }
    }

    private boolean isValidIsbn(String isbn) {
        return (isbn.length() == 10 || isbn.length() == 13 && isbn.chars().allMatch(Character::isDigit));
    }

    public int getValidIntOptions(String message) {
        int value;
        while (true) {
            System.out.println(message);
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                scanner.nextLine();
                break;
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        }
        return value;
    }

    public Status getValidStatusOptions(String message) {
        while (true) {
            System.out.println(message);
            String statusInput = scanner.nextLine().toUpperCase().trim();
            try {
                return Status.valueOf(statusInput);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid status. Please enter one of: Available, Borrowed, Reserved, Lost, Damaged.");
            }
        }
    }

    public Set<String> getValidAuthorsOptions() {
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

    public Category getValidCategoryOptions() {
        while (true) {
            System.out.println("Enter category. Available categories: ");
            for (Category category : Category.values()) {
                System.out.println(category + " ");
            }
            System.out.println("Enter a category: ");

            String input = scanner.nextLine().trim().toUpperCase();
            try {
                return Category.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid category. Please try again.");
            }
        }
    }

    public int getValidYearOptions() {
        int newPublicationYear;
        int currentYear = Year.now().getValue();
        int minYear = 1450;

        while (true) {
            System.out.println("Enter publication year (" + minYear + " - " + currentYear + "); ");

            if (scanner.hasNextInt()) {
                newPublicationYear = scanner.nextInt();
                scanner.nextLine();
                if (newPublicationYear >= minYear && newPublicationYear <= currentYear) {
                    return newPublicationYear;
                } else {
                    System.out.println("Invalid year. Please enter a valid year between " + minYear + " and " + currentYear);
                }

            } else {
                System.out.println("Invalid input. Please enter a valid publication year.");
                scanner.nextLine();
            }
        }
    }

    public ShelfLocation getValidShelfLocationOptions() {
        while (true) {
            System.out.println("Enter shelf location. Available locations: ");
            for (ShelfLocation shelfLocation : ShelfLocation.values()) {
                System.out.println(shelfLocation + " ");
            }
            System.out.println("Enter a location: ");

            String input = scanner.nextLine().trim().toUpperCase();
            try {
                return ShelfLocation.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid location. Please try again.");
            }
        }
    }

    public BookColumn getValidBookColumnOptions() {
        while (true) {
            System.out.println("Enter book's column. Available locations: ");

            for (BookColumn bookColumn : BookColumn.values()) {
                System.out.println(bookColumn + " ");
            }
            System.out.println("Enter book's column: ");
            String input = scanner.nextLine().trim().toUpperCase();

            try {
                return BookColumn.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid column. Please try again.");
            }
        }
    }

}

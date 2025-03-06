package com.gr1tEnt.librarymanagementsystem.utils;

import com.gr1tEnt.librarymanagementsystem.model.Category;
import com.gr1tEnt.librarymanagementsystem.model.ShelfLocation;
import com.gr1tEnt.librarymanagementsystem.model.Status;
import com.gr1tEnt.librarymanagementsystem.service.BookService;

import java.time.Year;
import java.util.Scanner;
import java.util.UUID;

public class InputValidator {
    private final Scanner scanner;
    private final BookService bookService;

    public InputValidator(Scanner scanner, BookService bookService) {
        this.scanner = scanner;
        this.bookService = bookService;
    }

    public boolean getValidUuid(String input) {
        try {
            UUID.fromString(input);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

/*    public UUID getValidId() {
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
    }*/

    public boolean getValidIsbn(String isbn) {
        return (isbn.length() == 10 || isbn.length() == 13) && isbn.chars().allMatch(Character::isDigit);
    }

    public boolean getValidInt(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isValidStatus() {
        String statusInput = scanner.nextLine().toUpperCase().trim();
        try {
            Status.valueOf(statusInput);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean getValidCategory(String categoryInput) {
        try {
            Category.valueOf(categoryInput.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean getValidYear(int year) {
        int currentYear = Year.now().getValue();
        int minYear = 1450;

        return year <= currentYear && year >= minYear;
    }

    public boolean getValidShelfLocation(String shelfLocationInput) {
        try {
            ShelfLocation.valueOf(shelfLocationInput.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

/*    public String getValidStringOptions(String message) {
        while (true) {
            System.out.println(message);
            String input = scanner.nextLine();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("This value cannot be empty.");
        }
    }*/

}

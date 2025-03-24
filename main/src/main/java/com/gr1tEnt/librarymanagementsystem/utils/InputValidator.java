package com.gr1tEnt.librarymanagementsystem.utils;

import com.gr1tEnt.librarymanagementsystem.model.BookColumn;
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

    public boolean isValidUuid(String uuidInput) {
        try {
            UUID.fromString(uuidInput);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean isValidBookColumn(String column) {
        try {
            BookColumn.valueOf(column.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean isValidIsbn(String isbnInput) {
        if (isbnInput.length() == 10) {
            // Regex for ISBN-10 (9 digits + 1 digit or 'X')
            String regex10 = "[0-9]{9}[0-9X]$";
            if (isbnInput.matches(regex10)) {
                int sum = 0;
                for (int i = 0; i < 9; i++) {
                    sum += (isbnInput.charAt(i) - '0') * (i + 1);
                }

                // Handle the last char, which could be 'X' or a digit
                char lastChar = isbnInput.charAt(0);
                if (lastChar == 'X') {
                    sum += 10 * 10;
                } else {
                    sum += (lastChar - '0') * 10;
                }
                return sum % 11 == 0;
            }
        }
    }

    public boolean isValidInt(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isValidStatus(String statusInput) {
        try {
            Status.valueOf(statusInput.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean isValidCategory(String categoryInput) {
        try {
            Category.valueOf(categoryInput.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean isValidYear(int year) {
        int currentYear = Year.now().getValue();
        int minYear = 1450;

        return year <= currentYear && year >= minYear;
    }

    public boolean isValidShelfLocation(String shelfLocationInput) {
        try {
            ShelfLocation.valueOf(shelfLocationInput.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}

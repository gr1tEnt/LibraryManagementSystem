package com.gr1tEnt.librarymanagementsystem.utils;

import com.gr1tEnt.librarymanagementsystem.model.*;
import com.gr1tEnt.librarymanagementsystem.service.BookService;
import com.gr1tEnt.librarymanagementsystem.service.MemberManager;

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
            String regex10 = "^[0-9]{9}[0-9X]$";
            if (isbnInput.matches(regex10)) {
                int sum = 0;
                for (int i = 0; i < 9; i++) {
                    sum += (isbnInput.charAt(i) - '0') * (i + 1);
                }

                // Handle the last char, which could be 'X' or a digit
                char lastChar = isbnInput.charAt(9);
                if (lastChar == 'X') {
                    sum += 10 * 10;
                } else {
                    sum += (lastChar - '0') * 10;  //// Multiply the last digit by 10
                }
                return sum % 11 == 0;
            }

        } else if (isbnInput.length() == 13) {
            // Regex for ISBN-13 (13 digits)
            String regex13 = "^[0-9]{13}$";
            if (isbnInput.matches(regex13)) {
                // Calculate checksum for ISBN-13
                int sum = 0;
                for (int i = 0; i < 13; i++) {
                    int digit = isbnInput.charAt(i);
                    if (i % 2 == 0) {
                        sum += digit; // Odd positions multiply by 1
                    } else {
                        sum += digit * 3; // Even positions multiply by 3
                    }
                }
                return sum % 10 == 0;
            }
        }
        return false;
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

    public boolean isValidMembershipType(String membershipType) {
        try {
            MembershipType.valueOf(membershipType.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}

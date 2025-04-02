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
        return (isbnInput.length() == 10 || isbnInput.length() == 13) && isbnInput.chars().allMatch(Character::isDigit);
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

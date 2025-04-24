package com.gr1tEnt.librarymanagementsystem.utils;

import com.gr1tEnt.librarymanagementsystem.model.Category;
import com.gr1tEnt.librarymanagementsystem.model.MembershipType;
import com.gr1tEnt.librarymanagementsystem.model.ShelfLocation;
import com.gr1tEnt.librarymanagementsystem.model.Status;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.UUID;

public class InputHelper {
    private final Scanner scanner = new Scanner(System.in);
    private final InputValidator inputValidator;

    public InputHelper(InputValidator inputValidator) {
        this.inputValidator = inputValidator;
    }

    public String getValidStringInput(String message) {
        while (true) {
            System.out.println(message);
            String input = scanner.nextLine();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("This value cannot be empty.");
        }
    }

    public int getValidIntInput(String message) {
        while (true) {
            System.out.println(message);
            String input = scanner.nextLine().trim();
            if (inputValidator.isValidInt(input)) {
                return Integer.parseInt(input);
            }
            System.out.println("Invalid number. Please try again.");
        }
    }

    public int getValidYearInput() {
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

    public Category getValidCategoryInput() {
        while (true) {
            System.out.println("Enter category: ");
            String input = scanner.nextLine().trim();
            if (inputValidator.isValidCategory(input)) {
                return Category.valueOf(input.toUpperCase());
            }
            System.out.println("Invalid category. Please try again.");
        }
    }

    public ShelfLocation getValidShelfLocationInput() {
        while (true) {
            System.out.println("Enter shelf location: ");
            String input = scanner.nextLine().trim();
            if (inputValidator.isValidShelfLocation(input)) {
                return ShelfLocation.valueOf(input.toUpperCase());
            }
            System.out.println("Invalid location. Please try again.");
        }
    }

    public Status getValidStatusInput() {
        while (true) {
            System.out.println("Enter status: ");
            String input = scanner.nextLine().trim();
            if (inputValidator.isValidStatus(input)) {
                return Status.valueOf(input.toUpperCase());
            }
            System.out.println("Invalid status. Please try again.");
        }
    }

    public UUID getValidIdInput() {
        while (true) {
            System.out.println("Enter book's ID: ");
            String input = scanner.nextLine().trim();
            if (inputValidator.isValidUuid(input)) {
                return UUID.fromString(input);
            }
            System.out.println("Invalid UUID. Please try again.");
        }
    }

    public String getValidIsbnInput() {
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

    public String getValidBookColumnInput() {
        while (true) {
            System.out.println("Enter book column to update: ");
            String input = scanner.nextLine().trim();
            if (inputValidator.isValidBookColumn(input)) {
                return input;
            }
            System.out.println("Invalid column. Please try again.");
        }
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

    public MembershipType getValidMembershipType() {
        while (true) {
            System.out.println("Enter membership type: ");
            String input = scanner.nextLine().trim();
            if (inputValidator.isValidMembershipType(input)) {
                return MembershipType.valueOf(input);
            }
            System.out.println("Invalid membership type. Please try again.");
        }
    }

}

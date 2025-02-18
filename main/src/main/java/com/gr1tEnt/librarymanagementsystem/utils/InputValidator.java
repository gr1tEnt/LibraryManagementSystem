package com.gr1tEnt.librarymanagementsystem.utils;

import com.gr1tEnt.librarymanagementsystem.model.Category;
import com.gr1tEnt.librarymanagementsystem.model.ShelfLocation;
import com.gr1tEnt.librarymanagementsystem.model.Status;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.UUID;

public class InputValidator {
    private final Scanner scanner;

    public InputValidator(Scanner scanner) {
        this.scanner = scanner;
    }

    public UUID getValidId() {
        UUID value = null;
        while (value == null) {
            System.out.println("Enter book's ID: ");
            String input = scanner.nextLine();

            try {
                value = UUID.fromString(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Please enter a valid UUID.");
            }
        }
        return value;
    }

    public int getValidInt (String message) {
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

    public Status getValidStatus (String message) {
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

    public Category getValidCategory() {
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

    public int getValidYear() {
        int newPublicationYear;
        while (true) {
            System.out.println("Enter publication year: ");
            if (scanner.hasNextInt()) {
                newPublicationYear = scanner.nextInt();
                scanner.nextLine();
                return newPublicationYear;
            } else {
                System.out.println("Invalid input. Please enter a valid publication year.");
                scanner.nextLine();
            }
        }
    }

    public ShelfLocation getValidShelfLocation() {
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



}

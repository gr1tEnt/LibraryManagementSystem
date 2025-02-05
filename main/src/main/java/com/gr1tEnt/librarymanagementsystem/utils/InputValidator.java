package com.gr1tEnt.librarymanagementsystem.utils;

import com.gr1tEnt.librarymanagementsystem.model.Status;

import java.util.Scanner;

public class InputValidator {
    private final Scanner scanner;

    public InputValidator(Scanner scanner) {
        this.scanner = scanner;
    }

    public Long getValidLong (String message) {
        long value;
        while (true) {
            System.out.println(message);
            if (scanner.hasNextLong()) {
                value = scanner.nextLong();
                scanner.nextLine();
                break;
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
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

    public String getValidStatus (String message) {
        while (true) {
            System.out.println(message);
            String statusInput = scanner.nextLine().toUpperCase().trim();
            try {
                return Status.valueOf(statusInput).name();
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid status. Please enter one of: Available, Borrowed, Reserved, Lost, Damaged.");
                scanner.nextLine();
            }

        }
    }


}

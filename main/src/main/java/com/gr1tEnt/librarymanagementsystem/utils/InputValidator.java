package com.gr1tEnt.librarymanagementsystem.utils;

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
                return value;
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }

    public int getValidInt (String message) {
        int value;
        while (true) {
            System.out.println(message);
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                scanner.nextLine();
                return value;
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }



}

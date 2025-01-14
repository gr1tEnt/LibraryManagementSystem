package com.gr1tEnt.librarymanagementsystem.menu;

import java.util.Scanner;

public class LibraryMenu {
    Scanner scanner = new Scanner(System.in);
    int choice;
    public void start() {
        do {
            System.out.println("1. Book Management");
            System.out.println("2. Book Search by Properties");
            System.out.println("3. Member Management");
            System.out.println("4. Transaction Management");
            System.out.println("5. Report Generation");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    bookManagement(scanner);
                case 2:
                    searchBooks(scanner);
            }

        } while (choice != 0);
        scanner.close();
    }

    public static void bookManagement(Scanner scanner) {
        System.out.println("\nBook Management");
        System.out.println("1. Add new books");
        System.out.println("2. Update book information");
        System.out.println("3. Remove books");
        System.out.println("4. Track book copies");
        System.out.println("5. Mark books as damaged/lost");
        System.out.println("6. Show all books");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.println("New book added!");
            case 2:
                System.out.println("Book information has been updated!");
            case 3:
                System.out.println("Books removed!");
            case 4:
                System.out.println("Number of copies of books: ");
            case 5:
                System.out.println("These books were marked as lost/damaged");
            case 6:
                System.out.println("All books: ");
            default:
                System.out.println("Incorrect choice");
        }
    }

    public static void searchBooks(Scanner scanner) {
        System.out.println("\nBook Search by Properties");
        System.out.println("1. Search by title");
        System.out.println("2. Search by author");
        System.out.println("3. Search by genre");
        System.out.println("4. Search by ISBN");
        System.out.println("5. Search by publisher");
        System.out.println("6. Search by publication year");
        System.out.println("7. Show all books");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.println("Enter title for searching: ");
            case 2:
                System.out.println("Enter author for searching: ");
            case 3:
                System.out.println("Enter genre for searching: ");
            case 4:
                System.out.println("Enter ISBN for searching: ");
            case 5:
                System.out.println("Enter publisher for searching: ");
            case 6:
                System.out.println("Enter publication year for searching: ");
            case 7:
                System.out.println("All books: ");
            default:
                System.out.println("Incorrect choice");
        }
    }
}

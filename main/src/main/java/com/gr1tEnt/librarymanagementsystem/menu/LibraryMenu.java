package com.gr1tEnt.librarymanagementsystem.menu;

import com.gr1tEnt.librarymanagementsystem.service.BookService;

import java.util.*;
import com.gr1tEnt.librarymanagementsystem.service.IBookManagementSystem;

public class LibraryMenu {
    private final IBookManagementSystem bookManagementSystem;
    private final Scanner scanner;


    public LibraryMenu(IBookManagementSystem bookManagementSystem, Scanner scanner) {
        this.bookManagementSystem = bookManagementSystem;
        this.scanner = scanner;
    }

    public void start() {
        int choice;
        do {
            System.out.println("1. Book Management");
            System.out.println("2. Book Search by Properties");
            System.out.println("3. Member Management");
            System.out.println("4. Transaction Management");
            System.out.println("5. Report Generation");
            System.out.println("6. Exit");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> bookManagement();
                case 2 -> searchBooks(scanner);
            }

        } while (choice != 6);
    }

    public void bookManagement(){
        System.out.println("\nBook Management");
        System.out.println("1. Add new books");
        System.out.println("2. Update book information");
        System.out.println("3. Remove books");
        System.out.println("4. Track book copies");
        System.out.println("5. Set new status");
        System.out.println("6. Show all books");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> bookManagementSystem.addNewBook();
            case 2 -> bookManagementSystem.updateBook();
            case 3 -> bookManagementSystem.removeBook();
            case 4 -> bookManagementSystem.trackCopies();
            case 5 -> bookManagementSystem.updateStatus();
            case 6 -> BookService.printAllBooks(BookService.getAllBooks());
            default -> System.out.println("Incorrect choice.");
        }
    }


//Search by Properties in development
    private static void searchBooks(Scanner scanner) {
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
            case 1 -> System.out.println("Enter title for searching: ");
            case 2 -> System.out.println("Enter author for searching: ");
            case 3 -> System.out.println("Enter genre for searching: ");
            case 4 -> System.out.println("Enter ISBN for searching: ");
            case 5 -> System.out.println("Enter publisher for searching: ");
            case 6 -> System.out.println("Enter publication year for searching: ");
            default -> System.out.println("Incorrect choice");
        }
    }
}

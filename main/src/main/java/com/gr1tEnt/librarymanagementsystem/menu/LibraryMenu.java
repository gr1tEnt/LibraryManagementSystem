package com.gr1tEnt.librarymanagementsystem.menu;

import com.gr1tEnt.librarymanagementsystem.service.BookService;

import java.util.*;
import com.gr1tEnt.librarymanagementsystem.service.IBookManagementSystem;
import com.gr1tEnt.librarymanagementsystem.service.IUpdateBookManagementSystem;

public class LibraryMenu {
    private final IBookManagementSystem bookManagementSystem;
    private final IUpdateBookManagementSystem updateBookManagementSystem;
    private final Scanner scanner = new Scanner(System.in);


    public LibraryMenu(IBookManagementSystem bookManagementSystem, IUpdateBookManagementSystem updateBookManagementSystem) {
        this.bookManagementSystem = bookManagementSystem;
        this.updateBookManagementSystem = updateBookManagementSystem;
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

    public void bookManagement() {
        System.out.println("\nBook Management");
        System.out.println("1. Add new books");
        System.out.println("2. Update book information");
        System.out.println("3. Remove books");
        System.out.println("4. Track book copies");
        System.out.println("5. Show all books");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> bookManagementSystem.addNewBook();
            case 2 -> updateBook();
            case 3 -> bookManagementSystem.removeBook();
            case 4 -> bookManagementSystem.trackCopies();
            case 5 -> BookService.printAllBooks();
            default -> System.out.println("Incorrect choice.");
        }
    }

    private void updateBook() {
        System.out.println("1. Update isbn");
        System.out.println("2. Update title");
        System.out.println("3. Update authors");
        System.out.println("4. Update publisher");
        System.out.println("5. Update year");
        System.out.println("6. Update category");
        System.out.println("7. Update number of copies");
        System.out.println("8. Update shelf location");
        System.out.println("9. Update status");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> updateBookManagementSystem.updateIsbn();
            case 2 -> updateBookManagementSystem.updateTitle();
            case 3 -> updateBookManagementSystem.updateAuthors();
            case 4 -> updateBookManagementSystem.updatePublisher();
            case 5 -> updateBookManagementSystem.updateYear();
            case 6 -> updateBookManagementSystem.updateCategory();
            case 7 -> updateBookManagementSystem.updateNumberOfCopies();
            case 8 -> updateBookManagementSystem.updateShelfLocation();
            case 9 -> updateBookManagementSystem.updateStatus();
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

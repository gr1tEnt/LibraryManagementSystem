package com.gr1tEnt.librarymanagementsystem.menu;

import com.gr1tEnt.librarymanagementsystem.model.Book;
import com.gr1tEnt.librarymanagementsystem.service.BookService;

import java.util.*;

public class LibraryMenu {
    static Scanner scanner = new Scanner(System.in);
    static int choice;
    public static void start() {
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
                case 1:
                    bookManagement(scanner);
                    break;
                case 2:
                    searchBooks(scanner);
                    break;
            }

        } while (choice != 6);
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
                addNewBook();
                break;
            case 2:
                System.out.println("Book information has been updated!");
                break;
            case 3:
                removeBook();
                break;
            case 4:
                System.out.println("Number of copies of books: ");
                break;
            case 5:
                System.out.println("These books were marked as lost/damaged");
                break;
            case 6:
                printAllBooks(BookService.getAllBooks());
                break;
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
    public static void addNewBook() {
        System.out.println("Enter ID: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Enter ISBN: ");
        String isbn = scanner.nextLine();

        System.out.println("Enter title: ");
        String title = scanner.nextLine();

        System.out.println("Enter authors: ");
        Set<String> authors = Collections.singleton(scanner.nextLine());

        System.out.println("Enter publisher: ");
        String publisher = scanner.nextLine();

        System.out.println("Enter year of publication: ");
        int publicationYear = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter category: ");
        String category = scanner.nextLine(); // I'll change categories to enum

        Book book = BookService.addBook(id, isbn, title, authors, publisher, publicationYear, category);

        System.out.println("Your new book: " + book);
    }

    public static void printAllBooks(Map<Long, Book> books) {
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
        } else {
            System.out.println("Listing all books: ");
            for (Book book : books.values()) {
                System.out.println(book);
            }
        }
    }
    public static void removeBook() {
        System.out.println("Enter book's ID to remove: ");
        long bookId = scanner.nextLong();
        boolean isRemoved = BookService.removeBook(bookId);
        if (isRemoved) {
            System.out.println("The book with ID " + bookId + " was removed.");
        } else {
            System.out.println("No book found with ID " + bookId + ".");
        }
    }
    
}

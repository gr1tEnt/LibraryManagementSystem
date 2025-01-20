package com.gr1tEnt.librarymanagementsystem.menu;

import com.gr1tEnt.librarymanagementsystem.model.Book;
import com.gr1tEnt.librarymanagementsystem.model.Category;
import com.gr1tEnt.librarymanagementsystem.model.Status;
import com.gr1tEnt.librarymanagementsystem.service.AuthorsService;
import com.gr1tEnt.librarymanagementsystem.service.BookService;
import com.gr1tEnt.librarymanagementsystem.service.CategoryService;
import com.gr1tEnt.librarymanagementsystem.service.PublicationYearService;

import java.util.*;

public class LibraryMenu {
    public static Scanner scanner = new Scanner(System.in);
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

    private static void bookManagement(Scanner scanner) {
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
            case 1:
                addNewBook();
                break;
            case 2:
                updateBook();
                break;
            case 3:
                removeBook();
                break;
            case 4:
                trackCopies();
                break;
            case 5:
                updateStatus();
                break;
            case 6:
                printAllBooks(BookService.getAllBooks());
                break;
            default:
                System.out.println("Incorrect choice.");
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
            default:
                System.out.println("Incorrect choice");
        }
    }

    private static void addNewBook() {
        System.out.println("Enter ID: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Enter ISBN: ");
        String isbn = scanner.nextLine();

        System.out.println("Enter title: ");
        String title = scanner.nextLine();

        System.out.println("Enter publisher: ");
        String publisher = scanner.nextLine();

        Book book = BookService.addBook(id, isbn, title, publisher);

        System.out.println("Your new book: " + book);
    }

    private static void printAllBooks(Map<Long, Book> books) {
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
        } else {
            System.out.println("Listing all books: ");
            for (Book book : books.values()) {
                System.out.println(book);
            }
        }
    }

    private static void removeBook() {
        System.out.println("Enter book's ID to remove: ");
        long bookId = scanner.nextLong();
        boolean isRemoved = BookService.removeBook(bookId);
        if (isRemoved) {
            System.out.println("The book with ID " + bookId + " was removed.");
        } else {
            System.out.println("No book found with ID " + bookId + ".");
        }
    }

    private static void updateStatus() {
        System.out.println("Enter book's ID: ");
        Long bookId = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Enter new status (Available, Borrowed, Reserved, Lost, Damaged): ");
        String statusInput = scanner.nextLine();
        try {
            Status newStatus = Status.valueOf(statusInput.toUpperCase());
            BookService.updateBookStatus(bookId, newStatus);

            System.out.println("The status has been updated!");

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid status. Please enter one the following: Available, Borrowed, Reserved, Lost, Damaged.");
        }
    }

    // I'll add ability to update the individual properties
    private static void updateBook() {
        System.out.println("Enter book's ID to update: ");
        long bookId = scanner.nextLong();
        scanner.nextLine();

        if (!BookService.bookExists(bookId)) {
            System.out.println("Book with ID " + bookId + " not found");
            return;
        }

        System.out.println("Enter new isbn");
        String newIsbn = scanner.nextLine();

        System.out.println("Enter new title" );
        String newTitle = scanner.nextLine();

        Set<String> newAuthors = AuthorsService.getValidAuthors();

        System.out.println("Enter new publisher");
        String newPublisher = scanner.nextLine();

        int newPublicationYear = PublicationYearService.getValidYear();

        Category newCategory = CategoryService.getValidCategory();

        Book newBook = new Book(newIsbn, newTitle, newAuthors, newPublisher, newPublicationYear, newCategory);
        BookService.updateBook(bookId, newBook);
    }

    // I'll add ability to see the quantity of books, using title, authors etc.
    private static void trackCopies() {
        System.out.println("Enter book's ID: ");
        long bookId = scanner.nextLong();
        if (!BookService.bookExists(bookId)) {
            System.out.println("Book with ID " + bookId + " not found");
            return;
        }

        System.out.println("Enter quantity of copies: ");
        int quantityOfCopies = scanner.nextInt();
        BookService.trackBookCopies(bookId, quantityOfCopies);

    }
}

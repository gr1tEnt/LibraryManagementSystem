package com.gr1tEnt.librarymanagementsystem.service;

import com.gr1tEnt.librarymanagementsystem.model.Book;
import com.gr1tEnt.librarymanagementsystem.model.Status;

import java.util.Map;
import java.util.Scanner;

public class BookManagementSystem implements IBookManagementSystem {
    public final Scanner scanner;
    private final BookService bookService;

    public BookManagementSystem(BookService bookService, Scanner scanner) {
        this.bookService = bookService;
        this.scanner = scanner;
    }

    @Override
    public void addNewBook() {
        System.out.println("Enter ID: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Enter ISBN: ");
        String isbn = scanner.nextLine();

        System.out.println("Enter title: ");
        String title = scanner.nextLine();

        System.out.println("Enter publisher: ");
        String publisher = scanner.nextLine();

        Book book = bookService.addBook(id, isbn, title, publisher);

        System.out.println("Your new book: " + book);
    }


    @Override
    public void removeBook() {
        System.out.println("Enter book's ID to remove: ");
        long bookId = scanner.nextLong();
        boolean isRemoved = BookService.removeBook(bookId);
        if (isRemoved) {
            System.out.println("The book with ID " + bookId + " was removed.");
        } else {
            System.out.println("No book found with ID " + bookId + ".");
        }
    }

    // I'll add ability to update the individual properties
    @Override
    public void updateBook() {
        System.out.println("Enter book's ID to update: ");
        long bookId = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Enter new isbn");
        String newIsbn = scanner.nextLine();

        System.out.println("Enter new title" );
        String newTitle = scanner.nextLine();

        System.out.println("Enter new publisher");
        String newPublisher = scanner.nextLine();

        BookService.updateBook(bookId, newIsbn, newTitle, newPublisher);
    }

    @Override
    public void updateStatus() {
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

    // I'll add ability to see the quantity of books, using title, authors etc.
    @Override
    public void trackCopies() {
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


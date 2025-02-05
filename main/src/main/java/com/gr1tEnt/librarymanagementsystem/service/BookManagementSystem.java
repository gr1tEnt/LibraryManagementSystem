package com.gr1tEnt.librarymanagementsystem.service;

import com.gr1tEnt.librarymanagementsystem.model.Book;
import com.gr1tEnt.librarymanagementsystem.model.Status;
import com.gr1tEnt.librarymanagementsystem.utils.InputValidator;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BookManagementSystem implements IBookManagementSystem {
    public final Scanner scanner;
    private final BookService bookService;
    private final InputValidator inputValidator;

    public BookManagementSystem(BookService bookService, Scanner scanner, InputValidator inputValidator) {
        this.bookService = bookService;
        this.scanner = scanner;
        this.inputValidator = inputValidator;
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
        long bookId  = inputValidator.getValidInt("Enter book's ID: ");

        if(!BookService.bookExists(bookId)) {
            System.out.println("Book with ID " + bookId + "not  found.");
            return;
        }

        String newStatus = inputValidator.getValidStatus("Enter new status: ");
        BookService.updateBookStatus(bookId, Status.valueOf(newStatus));

        System.out.println("The status has benn updated!");
    }

    // I'll add ability to see the quantity of books, using title, authors etc.
    @Override
    public void trackCopies() {
        long bookId = inputValidator.getValidLong("Enter book's ID: ");

            if (!BookService.bookExists(bookId)) {
                System.out.println("Book with ID " + bookId + " not found");
                return;
            }
            int quantityOfCopies = inputValidator.getValidInt("Enter quantity of copies: ");
            BookService.trackBookCopies(bookId, quantityOfCopies);

        System.out.println("Number of copies updated successfully!");
    }

}


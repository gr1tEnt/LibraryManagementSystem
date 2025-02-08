package com.gr1tEnt.librarymanagementsystem.service;

import com.gr1tEnt.librarymanagementsystem.model.Book;
import com.gr1tEnt.librarymanagementsystem.model.Status;
import com.gr1tEnt.librarymanagementsystem.utils.InputValidator;

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
        long id = inputValidator.getValidId();

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
        long bookId = inputValidator.getValidId();
        boolean isRemoved = bookService.removeBook(bookId);
        if (isRemoved) {
            System.out.println("The book with ID " + bookId + " was removed.");
        } else {
            System.out.println("No book found with ID " + bookId + ".");
        }
    }

    // I'll add ability to update the individual properties
    @Override
    public void updateBook() {
        long bookId = inputValidator.getValidId();

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
        long bookId  = inputValidator.getValidId();

        if(!BookService.bookExists(bookId)) {
            System.out.println("Book with ID " + bookId + "not  found.");
            return;
        }

        String newStatus = inputValidator.getValidStatus("Enter new status: ");
        Book updatedBook = BookService.updateBookStatus(bookId, Status.valueOf(newStatus));

        System.out.println("The status has benn updated! \n" + updatedBook);
    }

    // I'll add ability to see the quantity of books, using title, authors etc.
    @Override
    public void trackCopies() {
        long bookId = inputValidator.getValidId();

        if (!BookService.bookExists(bookId)) {
            System.out.println("Book with ID " + bookId + " not found");
            return;
        }

        int quantityOfCopies = inputValidator.getValidInt("Enter quantity of copies: ");
        BookService.trackBookCopies(bookId, quantityOfCopies);

        System.out.println("Number of copies updated successfully!");
    }

}


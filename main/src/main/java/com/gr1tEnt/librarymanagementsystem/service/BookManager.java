package com.gr1tEnt.librarymanagementsystem.service;

import com.gr1tEnt.librarymanagementsystem.model.*;
import com.gr1tEnt.librarymanagementsystem.utils.InputHelper;

import java.util.Set;
import java.util.UUID;

public class BookManager implements BookManagerInterface {
    private BookService bookService;
    private InputHelper inputHelper;

    @Override
    public void addNewBook() {
        String isbn = inputHelper.getValidIsbnInput();
        String title = inputHelper.getValidStringInput("Enter title: ");
        Set<String> authors = inputHelper.getValidAuthors();
        String publisher = inputHelper.getValidStringInput("Enter publisher: ");
        int publicationYear = inputHelper.getValidYearInput();
        Category category = inputHelper.getValidCategoryInput();
        int numberOfCopies = inputHelper.getValidIntInput("Enter number of copies: ");
        ShelfLocation shelfLocation = inputHelper.getValidShelfLocationInput();
        Status status = inputHelper.getValidStatusInput();

        Book book = new Book(isbn, title, authors, publisher, publicationYear, category, numberOfCopies, shelfLocation, status);

        if (bookService.addBook(book)) {
            System.out.println("Book added successfully.");
        } else {
            System.out.println("Failed to add the book. Please check the input values.");
        }
    }

    @Override
    public void removeBookById() {
        UUID bookId = inputHelper.getValidIdInput();

        if (bookService.removeBook(bookId)) {
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("No book found with such ID.");
        }
    }

    public void updateBookField() {
        UUID bookId = inputHelper.getValidIdInput();
        String bookColumn = inputHelper.getValidBookColumnInput();

        System.out.println("Enter new value: ");
        Object newValue = null;

        switch (bookColumn) {
            case "isbn" -> newValue = inputHelper.getValidIsbnInput();
            case "title" -> newValue = inputHelper.getValidStringInput("Enter new title: ");
            case "authors" -> newValue = inputHelper.getValidAuthors();
            case "publisher" -> newValue = inputHelper.getValidStringInput("Enter new publisher: ");
            case "publication_year" -> newValue = inputHelper.getValidYearInput();
            case "category" -> newValue = inputHelper.getValidCategoryInput();
            case "number_of_copies" -> newValue = inputHelper.getValidIntInput("Enter new number of copies: ");
            case "shelf_location" -> newValue = inputHelper.getValidShelfLocationInput();
            case "status" -> newValue = inputHelper.getValidStatusInput();
            default -> System.out.println("Incorrect choice.");
        }

        if (bookService.updateBookField(bookId, bookColumn, newValue)) {
            System.out.println("Book updated successfully.");
        } else {
            System.out.println("No book found with such ID.");
        }
    }

    // I'll add ability to see the quantity of books, using title, authors etc.
    @Override
    public void trackCopies() {
        UUID bookId = inputHelper.getValidIdInput();
        bookService.trackBookCopies(bookId);
    }

}


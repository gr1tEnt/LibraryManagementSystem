package com.gr1tEnt.librarymanagementsystem.service;

import com.gr1tEnt.librarymanagementsystem.model.Book;
import com.gr1tEnt.librarymanagementsystem.model.Category;
import com.gr1tEnt.librarymanagementsystem.model.Status;

import java.util.*;

public class BookService {
    private static final Map<Long, Book> books = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);


    public static Book addBook(Long id, String isbn, String title, String publisher) {
        Set<String> authors = getValidAuthors();
        int publicationYear = getValidYear();
        Category category = getValidCategory();

        if (books.containsKey(id)) {
            throw new IllegalArgumentException("Book with ID " + id + " already exists");
        }

        Book book = new Book(id, isbn, title, authors, publisher, publicationYear, category);
        books.put(id, book);
        return book;
    }

    public static boolean removeBook(Long id) {
        if (books.containsKey(id)) {
            Book removedBook = books.remove(id);
            System.out.println("Book removed " + removedBook);
            return true;
        } else {
            System.out.println("Invalid book's ID. Please try again");
            return false;
        }
    }

    public static Map<Long, Book> getAllBooks() {
        return books;
    }

    public static void updateBookStatus(Long id, Status newStatus) {
        Book book = books.get(id);
        if (book != null) {
            book.setStatus(newStatus);
            System.out.println("The status of the book has been updated: " + book);
        } else {
            System.out.println("Invalid book's ID. Please try again.");
        }
    }

    public static void updateBook(long bookId, String newIsbn, String newTitle, String newPublisher) {
        Set<String> authors = getValidAuthors();
        int publicationYear = getValidYear();
        Category category = getValidCategory();

        if (books.containsKey(bookId)) {
            Book updatedBook = new Book(newIsbn, newTitle, authors, newPublisher, publicationYear, category);
            books.replace(bookId, updatedBook);
            System.out.println("Book has been updated: " + updatedBook);
        } else {
            System.out.println("Book with ID " + bookId + " not found");
        }
    }

    public static void trackBookCopies(Long bookId, int quantityOfCopies) {
        Book book = books.get(bookId);

        if (book != null) {
            book.setNumberOfCopies(quantityOfCopies);
            System.out.println("Number of copies has benn updated: " + book);
        } else {
            System.out.println("Book with ID " + bookId + " not found.");
        }

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

    public static Set<String> getValidAuthors() {
        Set<String> authors = new HashSet<>();
        while (true) {
            System.out.println("Enter author (type 'done' to finish):");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("done")) {
                break;
            }
            if (!input.isEmpty()) {
                authors.add(input);
            } else {
                System.out.println("Author name cannot be empty. Please try again.");
            }
        }
        return authors;
    }

    public static Category getValidCategory() {
        while (true) {
            System.out.println("Enter category. Available categories: ");
            for (Category category : Category.values()) {
                System.out.println(category + " ");
            }
            System.out.println("Enter a category: ");

            String input = scanner.nextLine().trim().toUpperCase();
            try {
                return Category.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid category. Please try again.");
            }
        }
    }

    public static int getValidYear() {
        int newPublicationYear;
        while (true) {
            System.out.println("Enter publication year: ");
            if (scanner.hasNextInt()) {
                newPublicationYear = scanner.nextInt();
                scanner.nextLine();
                return newPublicationYear;
            } else {
                System.out.println("Invalid input. Please enter a valid publication year.");
                scanner.nextLine();
            }
        }
    }

    public static boolean bookExists(Long id) {
        return books.containsKey(id);
    }
}

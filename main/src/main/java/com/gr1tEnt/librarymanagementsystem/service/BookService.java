package com.gr1tEnt.librarymanagementsystem.service;

import com.gr1tEnt.librarymanagementsystem.database.DatabaseConnection;
import com.gr1tEnt.librarymanagementsystem.model.Book;
import com.gr1tEnt.librarymanagementsystem.model.Category;
import com.gr1tEnt.librarymanagementsystem.model.Status;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.*;

public class BookService {
    private static final List<Book> books = new ArrayList<>();

    public static void addBook(Book book) {
        String sql = "INSERT INTO books (isbn, title, authors, publisher, publication_year, category, number_of_copies, shelfLocation, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, book.getIsbn());
            stmt.setString(2, book.getTitle());
            stmt.setString(3, book.getAuthors().toString());
            stmt.setString(4, book.getPublisher());
            stmt.setInt(5, book.getPublicationYear());
            stmt.setString(6, book.getCategory().name());
            stmt.setInt(7, book.getNumberOfCopies());
            stmt.setString(8, book.getShelfLocation());
            stmt.setString(9, book.getStatus().name());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Failed to insert book.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    book.setId(generatedKeys.getLong(1));
                    System.out.println("Book added with ID " + book.getId());
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void removeBook(Long id) {
        String sql = "DELETE FROM books WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                System.out.println("No book found with id " + id);
            } else {
                System.out.println("Book deleted successfully");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Book> getAllBooks() {
        String sql = "SELECT * FROM books";
        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Book book = new Book(
                        rs.getLong("id"),
                        rs.getString("isbn"),
                        rs.getString("title"),
                        Collections.singleton(rs.getString("authors")),
                        rs.getString("publisher"),
                        rs.getInt("publication_year"),
                        Category.valueOf(rs.getString("category")),
                        rs.getInt("number_of_copies"),
                        rs.getString("shelfLocation"),
                        Status.valueOf(rs.getString("status"))
                );
                books.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    public static void updateBookStatus(Long id, Status newStatus) {
        String sql = "UPDATE books SET status = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newStatus.name());
            stmt.setLong(2, id);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("No book found with id " + id);
            } else {
                System.out.println("Book status has been updated.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

/*    public static void updateBook(long bookId, String newIsbn, String newTitle, String newPublisher) {

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
*/

/*    public static void trackBookCopies(Long bookId, int quantityOfCopies) {
        Book book = books.get(bookId);

        if (book != null) {
            book.setNumberOfCopies(quantityOfCopies);
            System.out.println("Number of copies has benn updated: " + book);
        } else {
            System.out.println("Book with ID " + bookId + " not found.");
        }
    }*/

    public static void printAllBooks() {
        List<Book> books = getAllBooks();

        if (books == null || books.isEmpty()) {
            System.out.println("No books available in the library.");
        } else {
            System.out.println("Listing all books: ");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    public static boolean bookExists(Long id) {
        return books.contains(id);
    }
}

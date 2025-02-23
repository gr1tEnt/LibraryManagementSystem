package com.gr1tEnt.librarymanagementsystem.service;

import com.gr1tEnt.librarymanagementsystem.database.DatabaseConnection;
import com.gr1tEnt.librarymanagementsystem.model.Book;
import com.gr1tEnt.librarymanagementsystem.model.Category;
import com.gr1tEnt.librarymanagementsystem.model.ShelfLocation;
import com.gr1tEnt.librarymanagementsystem.model.Status;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.*;

public class BookService {
    private static final List<Book> books = new ArrayList<>();
    private final Connection conn;

    public BookService(Connection conn) {
        this.conn = conn;
    }

    public boolean addBook(Book book) {
        String sql = "INSERT INTO books (id, isbn, title, authors, publisher, publication_year, category, number_of_copies, shelf_location, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, book.getId().toString());
            stmt.setString(2, book.getIsbn());
            stmt.setString(3, book.getTitle());
            stmt.setString(4, book.getAuthors().toString());
            stmt.setString(5, book.getPublisher());
            stmt.setInt(6, book.getPublicationYear());
            stmt.setString(7, book.getCategory().name());
            stmt.setInt(8, book.getNumberOfCopies());
            stmt.setString(9, book.getShelfLocation().name());
            stmt.setString(10, book.getStatus().name());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean removeBook(UUID bookId) {
        String sql = "DELETE FROM books WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, bookId.toString());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Book> getAllBooks() {
        String sql = "SELECT id, isbn, title, authors, publisher, publication_year, category, number_of_copies, shelf_location, status FROM books";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Book book = new Book(
                        UUID.fromString(rs.getString("id")),
                        rs.getString("isbn"),
                        rs.getString("title"),
                        Collections.singleton(rs.getString("authors")),
                        rs.getString("publisher"),
                        rs.getInt("publication_year"),
                        Category.valueOf(rs.getString("category")),
                        rs.getInt("number_of_copies"),
                        ShelfLocation.valueOf(rs.getString("shelf_location")),
                        Status.valueOf(rs.getString("status"))
                );
                books.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    public boolean updateBookField(UUID bookId, String bookColumn, Object newValue) {
        String sql = "UPDATE books SET " + bookColumn + " = ? WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, String.valueOf(newValue));
            stmt.setString(2, String.valueOf(bookId));

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void trackBookCopies(UUID bookId) {
        String sql = "SELECT number_of_copies FROM books WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, bookId.toString());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int copies = rs.getInt("number_of_copies");
                    System.out.println("Number of copies: " + copies);
                } else {
                    System.out.println("No book found with ID " + bookId);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void printAllBooks() {
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

    public boolean isBookExists(UUID bookId) {
        String sql = "SELECT COUNT(*) FROM books WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, bookId.toString());
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}

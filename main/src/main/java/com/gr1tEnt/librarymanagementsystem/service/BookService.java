package com.gr1tEnt.librarymanagementsystem.service;

import com.gr1tEnt.librarymanagementsystem.database.DatabaseConnection;
import com.gr1tEnt.librarymanagementsystem.model.Book;
import com.gr1tEnt.librarymanagementsystem.model.Category;
import com.gr1tEnt.librarymanagementsystem.model.ShelfLocation;
import com.gr1tEnt.librarymanagementsystem.model.Status;

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
            stmt.setString(8, book.getShelfLocation().name());
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

    public static void removeBook(Long bookId) {
        String sql = "DELETE FROM books WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, bookId);
            checkUpdateResult(bookId, stmt);

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
                        ShelfLocation.valueOf(rs.getString("shelfLocation")),
                        Status.valueOf(rs.getString("status"))
                );
                books.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    // AAALSO I'm not sure if update methods should be in this class, technically it's ok, but there are many of them here
    public static void updateBookStatus(Long bookId, Status newStatus) {
        String sql = "UPDATE books SET status = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newStatus.name());
            stmt.setLong(2, bookId);

            checkUpdateResult(bookId, stmt);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateIsbn(Long bookId, String newIsbn) {
        String sql = "UPDATE books SET isbn = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newIsbn);
            stmt.setLong(2, bookId);

            checkUpdateResult(bookId, stmt);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateTitle(Long bookId, String newTitle) {
        String sql = "UPDATE books SET title = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newTitle);
            stmt.setLong(2, bookId);

            checkUpdateResult(bookId, stmt);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateAuthors(Long bookId, Set<String> newAuthors) {
        String sql = "UPDATE books SET authors = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newAuthors.toString());
            stmt.setLong(2, bookId);

            checkUpdateResult(bookId, stmt);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updatePublisher(Long bookId, String newPublisher) {
        String sql = "UPDATE books SET publisher = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newPublisher);
            stmt.setLong(2, bookId);

            checkUpdateResult(bookId, stmt);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateYear(Long bookId, int newYear) {
        String sql = "UPDATE books SET publication_year = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, newYear);
            stmt.setLong(2, bookId);

            checkUpdateResult(bookId, stmt);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateCategory(Long bookId, Category newCategory) {
        String sql = "UPDATE books SET category = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newCategory.name());
            stmt.setLong(2, bookId);

            checkUpdateResult(bookId, stmt);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateNumberOfCopies(Long bookId, int newNumberOfCopies) {
        String sql = "UPDATE books SET number_of_copies = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, newNumberOfCopies);
            stmt.setLong(2, bookId);

            checkUpdateResult(bookId, stmt);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    
    // I'm not sure if this method should be here
    private static void checkUpdateResult(Long bookId, PreparedStatement stmt) throws SQLException {
        int affectedRows = stmt.executeUpdate();
        if (affectedRows == 0) {
            System.out.println("No book found with id " + bookId);
        } else {
            System.out.println("Books has been updated.");
        }
    }

    public static void trackBookCopies(Long bookId) {
        String sql = "SELECT number_of_copies FROM books WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, bookId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()){
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

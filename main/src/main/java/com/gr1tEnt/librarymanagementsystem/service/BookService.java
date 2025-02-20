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
    private final Connection conn;

    public BookService(Connection conn) {
        this.conn = conn;
    }

    public void addBook(Book book) {
        String sql = "INSERT INTO books (id, isbn, title, authors, publisher, publication_year, category, number_of_copies, shelfLocation, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            if (book.getId() == null) {
                book.setId(UUID.randomUUID());
            }

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
            if (affectedRows == 0) {
                throw new SQLException("Failed to insert book.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    book.setId(UUID.fromString(generatedKeys.getString(1)));
                    System.out.println("Book added with ID " + book.getId());
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeBook(UUID bookId) {
        String sql = "DELETE FROM books WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, bookId.toString());

            checkUpdateResult(bookId, stmt);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Book> getAllBooks() {
        String sql = "SELECT * FROM books";

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
    public void updateBookStatus(UUID bookId, Status newStatus) {
        String sql = "UPDATE books SET status = ? WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newStatus.name());
            stmt.setString(2, bookId.toString());

            checkUpdateResult(bookId, stmt);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateIsbn(UUID bookId, String newIsbn) {
        String sql = "UPDATE books SET isbn = ? WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newIsbn);
            stmt.setString(2, bookId.toString());

            checkUpdateResult(bookId, stmt);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateTitle(UUID bookId, String newTitle) {
        String sql = "UPDATE books SET title = ? WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newTitle);
            stmt.setString(2, bookId.toString());

            checkUpdateResult(bookId, stmt);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateAuthors(UUID bookId, Set<String> newAuthors) {
        String sql = "UPDATE books SET authors = ? WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newAuthors.toString());
            stmt.setString(2, bookId.toString());

            checkUpdateResult(bookId, stmt);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePublisher(UUID bookId, String newPublisher) {
        String sql = "UPDATE books SET publisher = ? WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newPublisher);
            stmt.setString(2, bookId.toString());

            checkUpdateResult(bookId, stmt);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateYear(UUID bookId, int newYear) {
        String sql = "UPDATE books SET publication_year = ? WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, newYear);
            stmt.setString(2, bookId.toString());

            checkUpdateResult(bookId, stmt);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateCategory(UUID bookId, Category newCategory) {
        String sql = "UPDATE books SET category = ? WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newCategory.name());
            stmt.setString(2, bookId.toString());

            checkUpdateResult(bookId, stmt);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateNumberOfCopies(UUID bookId, int newNumberOfCopies) {
        String sql = "UPDATE books SET number_of_copies = ? WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, newNumberOfCopies);
            stmt.setString(2, bookId.toString());

            checkUpdateResult(bookId, stmt);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateShelfLocation(UUID bookId, ShelfLocation newShelfLocation) {
        String sql = "UPDATE books SET shelfLocation = ? WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newShelfLocation.name());
            stmt.setString(2, bookId.toString());

            checkUpdateResult(bookId, stmt);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // I'm not sure if this method should be here
    private void checkUpdateResult(UUID bookId, PreparedStatement stmt) throws SQLException {
        int affectedRows = stmt.executeUpdate();
        if (affectedRows == 0) {
            System.out.println("No book found with id " + bookId);
        } else {
            System.out.println("Books has been updated.");
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

    public static boolean bookExists(Long id) {
        return books.contains(id);
    }
}

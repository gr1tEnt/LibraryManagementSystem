package com.gr1tEnt.librarymanagementsystem.model;
import java.util.Set;
import java.util.UUID;

public class Book {
    private UUID id = UUID.randomUUID();
    private String isbn;
    private String title;
    private Set<String> authors;
    private String publisher;
    private int publicationYear;
    private Category category;
    private int numberOfCopies;
    private ShelfLocation shelfLocation;
    private Status status;

    public Book(String isbn, String title, Set<String> authors, String publisher, int publicationYear, Category category, int numberOfCopies, ShelfLocation shelfLocation, Status status) {
        this.isbn = isbn;
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.category = category;
        this.numberOfCopies = numberOfCopies;
        this.shelfLocation = shelfLocation;
        this.status = status;
    }

    public Book(UUID id, String isbn, String title, Set<String> authors, String publisher, int publicationYear, Category category, int numberOfCopies, ShelfLocation shelfLocation, Status status) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.category = category;
        this.numberOfCopies = numberOfCopies;
        this.shelfLocation = shelfLocation;
        this.status = status;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public Set<String> getAuthors() {
        return authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public Category getCategory() {
        return category;
    }

    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    public ShelfLocation getShelfLocation() {
        return shelfLocation;
    }

    public Status getStatus() {
        return status;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setNumberOfCopies(int numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String toString() {
        return "Book{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                ", publisher='" + publisher + '\'' +
                ", publicationYear=" + publicationYear +
                ", category='" + category + '\'' +
                ", numberOfCopies=" + numberOfCopies +
                ", shelfLocation='" + shelfLocation + '\'' +
                ", status=" + status +
                '}';
    }
}

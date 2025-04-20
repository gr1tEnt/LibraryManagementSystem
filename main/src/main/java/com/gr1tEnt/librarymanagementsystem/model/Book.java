package com.gr1tEnt.librarymanagementsystem.model;
import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @Column(name = "id")
    private UUID id = UUID.randomUUID();

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "title")
    private String title;

    @Column(name = "authors")
    private Set<String> authors;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "publication_year")
    private int publicationYear;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private Category category;

    @Column(name = "number_of_copies")
    private int numberOfCopies;

    @Enumerated(EnumType.STRING)
    @Column(name = "shelf_location")
    private ShelfLocation shelfLocation;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    // Constructor for JPA
    public Book() {
    }

    // Constructor for adding a book
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

    // Constructor for retrieving a book
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

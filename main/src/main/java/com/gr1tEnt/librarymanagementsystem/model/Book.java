package com.gr1tEnt.librarymanagementsystem.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "books")
@NoArgsConstructor // Constructor for JPA
@Getter
@Setter
@ToString
public class Book {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "isbn", nullable = false)
    private String isbn;

    @Column(name = "title", nullable = false)
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

}

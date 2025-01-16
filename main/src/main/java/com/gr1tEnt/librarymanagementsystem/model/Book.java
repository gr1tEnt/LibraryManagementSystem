package com.gr1tEnt.librarymanagementsystem.model;
import jakarta.persistence.*;
import java.util.Set;

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
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

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(nullable = false)
    private String title;

    @ElementCollection
    @CollectionTable(name = "book_authors", joinColumns = @JoinColumn(name = "book_id"))
    @Column(name = "author")
    private Set<String> authors;

    @Column(nullable = false)
    private String publisher;

    @Column(nullable = false)
    private int publicationYear;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private String category;

    @Column(nullable = false)
    private int numberOfCopies;

    @Column(nullable = false)
    private String shelfLocation;

    @Column(nullable = false)
    private Status status;

    public Book(Long id, String isbn, String title, Set<String> authors, String publisher, int publicationYear, String category) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.status = Status.Available;
        this.category = category;
        this.numberOfCopies = 0;
    }

    public Book() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<String> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<String> authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(int numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    public String getShelfLocation() {
        return shelfLocation;
    }

    public void setShelfLocation(String shelfLocation) {
        this.shelfLocation = shelfLocation;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

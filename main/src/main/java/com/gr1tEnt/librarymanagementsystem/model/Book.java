package com.gr1tEnt.librarymanagementsystem.model;
import java.util.Set;

public class Book {
    private Long id;
    private String isbn;
    private String title;
    private Set<String> authors;
    private String publisher;
    private int publicationYear;
    private Category category;
    private int numberOfCopies;
    private String shelfLocation;
    private Status status;

    public Book(Long id, String isbn, String title, Set<String> authors, String publisher, int publicationYear, Category category) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.status = Status.AVAILABLE;
        this.category = category;
        this.numberOfCopies = 0;
    }

    public Book(String isbn, String title, Set<String> authors, String publisher, int publicationYear, Category category) {
        this.isbn = isbn;
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.category = category;
        this.numberOfCopies = 0;
        this.status = Status.AVAILABLE;
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

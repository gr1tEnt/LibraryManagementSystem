package com.gr1tEnt.librarymanagementsystem.model;
import java.util.Set;

public class Book {
    private Long id;

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

    public Book() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

package com.gr1tEnt.librarymanagementsystem.service;

import com.gr1tEnt.librarymanagementsystem.model.Book;
import com.gr1tEnt.librarymanagementsystem.model.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BookService {
    private static final List<Book> books = new ArrayList<>();

    public static Book addBook(Long id, String isbn, String title, Set<String> authors, String publisher, int publicationYear, Genre category) {
        Book book = new Book(id, isbn, title, authors, publisher, publicationYear, category);
        books.add(book);
        return book;
    }
}

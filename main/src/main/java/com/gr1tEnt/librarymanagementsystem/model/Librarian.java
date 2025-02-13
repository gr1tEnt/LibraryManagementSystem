package com.gr1tEnt.librarymanagementsystem.model;

public class Librarian extends LibraryStaff {
    int numberOfBooksManaged;

    public Librarian(Long id, String name, String email, Role role, int numberOfBooksManaged) {
        super(id, name, email, role);
        this.numberOfBooksManaged = numberOfBooksManaged;
    }

    public int getNumberOfBooksManaged() {
        return numberOfBooksManaged;
    }
}

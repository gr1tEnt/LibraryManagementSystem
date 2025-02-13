package com.gr1tEnt.librarymanagementsystem.model;

public class LibraryStaff {
    protected Long id;
    protected String name;
    protected String email;
    protected Role role;

    public LibraryStaff(Long id, String name, String email, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }
}

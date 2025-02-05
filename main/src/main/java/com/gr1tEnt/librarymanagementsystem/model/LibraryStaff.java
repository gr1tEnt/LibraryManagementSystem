package com.gr1tEnt.librarymanagementsystem.model;

public class LibraryStaff {
    private Long id;
    private String name;
    private String email;
    private String role; // e.g., Librarian, Assistant

    public LibraryStaff() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

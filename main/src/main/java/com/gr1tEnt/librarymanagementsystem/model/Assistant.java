package com.gr1tEnt.librarymanagementsystem.model;

public class Assistant extends LibraryStaff {
    public Assistant(Long id, String name, String email, Role role) {
        super(id, name, email, Role.ASSISTANT);
    }
}

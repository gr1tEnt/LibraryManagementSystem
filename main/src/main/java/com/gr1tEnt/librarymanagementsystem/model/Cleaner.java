package com.gr1tEnt.librarymanagementsystem.model;

public class Cleaner extends LibraryStaff {
    boolean isCompleteCleaning;

    public Cleaner(Long id, String name, String email, Role role) {
        super(id, name, email, Role.CLEANER);
    }

    public boolean isCompleteCleaning() {
        return isCompleteCleaning;
    }
}

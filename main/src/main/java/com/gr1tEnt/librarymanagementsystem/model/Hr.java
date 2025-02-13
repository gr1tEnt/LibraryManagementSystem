package com.gr1tEnt.librarymanagementsystem.model;

public class Hr extends LibraryStaff {
    int numberOFHiredPeople;

    public Hr(Long id, String name, String email, Role role) {
        super(id, name, email, Role.HR);
    }

    public int getNumberOFHiredPeople() {
        return numberOFHiredPeople;
    }
}

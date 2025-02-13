package com.gr1tEnt.librarymanagementsystem.model;

import java.time.LocalDate;
import java.util.Set;

public class Member {

    private Long memberId;
    private String name;
    private String contactInfo; // Could be phone/email
    private MembershipType membershipType;
    private LocalDate joinDate;
    private Set<Transaction> currentBorrowings;
    private Set<Transaction> borrowingHistory;
}

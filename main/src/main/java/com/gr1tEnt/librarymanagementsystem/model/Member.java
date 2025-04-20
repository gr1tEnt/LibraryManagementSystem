package com.gr1tEnt.librarymanagementsystem.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public class Member {
    private UUID memberId = UUID.randomUUID();
    private String name;
    private String contactInfo; // Could be phone/email
    private MembershipType membershipType = MembershipType.GUEST;
    private LocalDate joinDate = LocalDate.now();
    private Set<Transaction> transactions;

    // Constructor for creating a mew member
    public Member(String name, String contactInfo) {
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public UUID getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public MembershipType getMembershipType() {
        return membershipType;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", name='" + name + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                ", membershipType=" + membershipType +
                ", joinDate=" + joinDate +
                ", transactions=" + transactions +
                '}';
    }
}

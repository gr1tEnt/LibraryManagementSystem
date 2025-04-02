package com.gr1tEnt.librarymanagementsystem.model;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public class Member {
    private UUID memberId = UUID.randomUUID();
    private String name;
    private String contactInfo; // Could be phone/email
    private MembershipType membershipType;
    private LocalDate joinDate;
    private Set<Transaction> transactions;

    public Member(UUID memberId, String name, String contactInfo, MembershipType membershipType) {
        this.memberId = memberId;
        this.name = name;
        this.contactInfo = contactInfo;
        this.membershipType = membershipType;
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

package com.gr1tEnt.librarymanagementsystem;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String contactInfo; // Could be phone/email

    @Column(nullable = false)
    private String membershipType; // e.g., Regular, Premium

    @Column(nullable = false)
    private LocalDate joinDate;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private Set<Transaction> currentBorrowings;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private Set<Transaction> borrowingHistory;

    public Member() {}

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public Set<Transaction> getCurrentBorrowings() {
        return currentBorrowings;
    }

    public void setCurrentBorrowings(Set<Transaction> currentBorrowings) {
        this.currentBorrowings = currentBorrowings;
    }

    public Set<Transaction> getBorrowingHistory() {
        return borrowingHistory;
    }

    public void setBorrowingHistory(Set<Transaction> borrowingHistory) {
        this.borrowingHistory = borrowingHistory;
    }
}

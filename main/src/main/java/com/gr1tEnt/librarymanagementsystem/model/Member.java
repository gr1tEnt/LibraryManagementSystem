package com.gr1tEnt.librarymanagementsystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "members")
@NoArgsConstructor // Constructor for JPA
@Getter
@Setter
@ToString
public class Member {
    @Id
    @Column(name = "member_id")
    private UUID memberId = UUID.randomUUID();

    @Column(name = "name")
    private String name;

    @Column(name = "contact_info")
    private String contactInfo; // Could be phone/email

    @Column(name = "membership_type")
    @Enumerated(EnumType.STRING)
    private MembershipType membershipType = MembershipType.GUEST;

    @Column(name = "join_date")
    private LocalDate joinDate = LocalDate.now();

    @OneToMany(mappedBy = "member_id") // member_id from transactions
    private List<Transaction> transactions;

    // Constructor for creating a mew member
    public Member(String name, String contactInfo) {
        this.name = name;
        this.contactInfo = contactInfo;
    }
}

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
    @Column(name = "member_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID memberId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "contact_info")
    private String contactInfo; // Could be phone/email

    @Column(name = "membership_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private MembershipType membershipType = MembershipType.GUEST;

    @Column(name = "join_date", nullable = false)
    private LocalDate joinDate = LocalDate.now();

    @OneToMany(mappedBy = "member_id", fetch = FetchType.LAZY) // member_id from transactions
    private List<Transaction> transactions;

    // Constructor for creating a mew member
    public Member(String name, String contactInfo) {
        this.name = name;
        this.contactInfo = contactInfo;
    }
}

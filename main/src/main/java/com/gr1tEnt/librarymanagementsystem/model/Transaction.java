package com.gr1tEnt.librarymanagementsystem.model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@NoArgsConstructor // Constructor for JPA
@Getter
@Setter
@ToString
public class Transaction {
    @Id
    @Column(name = "transaction_id")
    private UUID transactionId = UUID.randomUUID();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @Enumerated(EnumType.STRING)
    @Column(name = "operation_type")
    private OperationType operation;

    @Column(name = "operation_date")
    private LocalDate date;
}

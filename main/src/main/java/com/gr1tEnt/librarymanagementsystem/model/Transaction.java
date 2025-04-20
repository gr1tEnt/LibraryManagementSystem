package com.gr1tEnt.librarymanagementsystem.model;
import java.time.LocalDate;
import java.util.UUID;

public class Transaction {
    private UUID transactionId = UUID.randomUUID();
    private UUID memberId;
    private UUID bookId;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private boolean isReturned;
}

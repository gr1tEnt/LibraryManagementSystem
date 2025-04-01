package com.gr1tEnt.librarymanagementsystem.model;
import java.time.LocalDate;

public class Transaction {
    private Long id;
    private Book book;
    private Member member;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private boolean isReturned;
}

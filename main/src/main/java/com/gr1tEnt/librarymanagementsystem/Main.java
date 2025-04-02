package com.gr1tEnt.librarymanagementsystem;

import com.gr1tEnt.librarymanagementsystem.database.DatabaseConnection;
import com.gr1tEnt.librarymanagementsystem.menu.LibraryMenu;
import com.gr1tEnt.librarymanagementsystem.service.BookManager;
import com.gr1tEnt.librarymanagementsystem.service.BookService;
import com.gr1tEnt.librarymanagementsystem.service.BookManagerInterface;
import com.gr1tEnt.librarymanagementsystem.utils.InputValidator;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection connection = DatabaseConnection.getConnection();
        BookService bookService = new BookService(connection);
        InputValidator inputValidator = new InputValidator(scanner, bookService);
        BookManagerInterface bookManagementSystem  = new BookManager();
        LibraryMenu menu = new LibraryMenu(bookManagementSystem, bookService);

        menu.start();
    }
}

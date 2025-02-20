package com.gr1tEnt.librarymanagementsystem;

import com.gr1tEnt.librarymanagementsystem.database.DatabaseConnection;
import com.gr1tEnt.librarymanagementsystem.menu.LibraryMenu;
import com.gr1tEnt.librarymanagementsystem.service.BookServiceController;
import com.gr1tEnt.librarymanagementsystem.service.BookService;
import com.gr1tEnt.librarymanagementsystem.service.BookServiceController;
import com.gr1tEnt.librarymanagementsystem.service.IBookServiceController;
import com.gr1tEnt.librarymanagementsystem.service.IUpdateBookServiceController;
import com.gr1tEnt.librarymanagementsystem.utils.InputValidator;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection connection = DatabaseConnection.getConnection();
        BookService bookService = new BookService(connection);
        InputValidator inputValidator = new InputValidator(scanner);
        IBookServiceController bookManagementSystem  = new BookServiceController(bookService, scanner, inputValidator);
        IUpdateBookServiceController updateBookManagementSystem = new BookServiceController(bookService, scanner, inputValidator);
        LibraryMenu menu = new LibraryMenu(bookManagementSystem, updateBookManagementSystem, bookService);

        menu.start();
    }
}

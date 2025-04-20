package com.gr1tEnt.librarymanagementsystem;

import com.gr1tEnt.librarymanagementsystem.database.DatabaseConnection;
import com.gr1tEnt.librarymanagementsystem.menu.LibraryMenu;
import com.gr1tEnt.librarymanagementsystem.service.BookManager;
import com.gr1tEnt.librarymanagementsystem.service.BookService;
import com.gr1tEnt.librarymanagementsystem.service.BookManagerInterface;
import com.gr1tEnt.librarymanagementsystem.utils.InputHelper;
import com.gr1tEnt.librarymanagementsystem.utils.InputValidator;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            throw new RuntimeException("Missing config filename parameter");
        }
        Scanner scanner = new Scanner(System.in);
        Connection connection = DatabaseConnection.getConnection();
        BookService bookService = new BookService(connection);
        InputValidator inputValidator = new InputValidator(scanner, bookService);
        InputHelper inputHelper = new InputHelper(inputValidator);
        BookManagerInterface bookManagementSystem  = new BookManager(bookService, inputHelper);
        LibraryMenu menu = new LibraryMenu(bookManagementSystem, bookService);

        menu.start();
    }
}

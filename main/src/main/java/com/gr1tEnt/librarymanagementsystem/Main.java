package com.gr1tEnt.librarymanagementsystem;

import com.gr1tEnt.librarymanagementsystem.menu.LibraryMenu;
import com.gr1tEnt.librarymanagementsystem.service.BookManagementSystem;
import com.gr1tEnt.librarymanagementsystem.service.BookService;
import com.gr1tEnt.librarymanagementsystem.service.IBookManagementSystem;
import com.gr1tEnt.librarymanagementsystem.utils.InputValidator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookService bookService = new BookService();
        InputValidator inputValidator = new InputValidator(scanner);

        IBookManagementSystem bookManagementSystem  = new BookManagementSystem(bookService, scanner, inputValidator);

        LibraryMenu menu = new LibraryMenu(bookManagementSystem, scanner);
        menu.start();
    }
}

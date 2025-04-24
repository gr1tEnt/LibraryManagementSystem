package com.gr1tEnt.librarymanagementsystem.menu;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MenuRunner implements CommandLineRunner {
    private LibraryMenu libraryMenu;

    public MenuRunner(LibraryMenu libraryMenu) {
        this.libraryMenu = libraryMenu;
    }

    @Override
    public void run(String... args) throws Exception {
        libraryMenu.start();
    }
}

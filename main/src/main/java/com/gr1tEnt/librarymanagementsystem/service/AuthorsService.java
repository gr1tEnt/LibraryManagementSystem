package com.gr1tEnt.librarymanagementsystem.service;

import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;

public class AuthorsService {
    private static final Scanner scanner = new Scanner(System.in);

    public static Set<String> getValidAuthors() {
        Set<String> authors = new HashSet<>();
        while (true) {
            System.out.println("Enter author (type 'done' to finish):");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("done")) {
                break;
            }
            if (!input.isEmpty()) {
                authors.add(input);
            } else {
                System.out.println("Author name cannot be empty. Please try again.");
            }
        }
        return authors;
    }

}

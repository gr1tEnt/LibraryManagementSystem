package com.gr1tEnt.librarymanagementsystem.service;

import com.gr1tEnt.librarymanagementsystem.model.Category;
import java.util.Scanner;


public class CategoryService {
    private static final Scanner scanner = new Scanner(System.in);

    public static Category getValidCategory() {
        while (true) {
            System.out.println("Enter category. Available categories: ");
            for (Category category : Category.values()) {
                System.out.println(category + " ");
            }
            System.out.println("Enter a category: ");

            String input = scanner.nextLine().trim().toUpperCase();
            try {
                return Category.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid category. Please try again.");
            }
        }
    }

}

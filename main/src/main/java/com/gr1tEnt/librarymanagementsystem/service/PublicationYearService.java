package com.gr1tEnt.librarymanagementsystem.service;
import java.util.Scanner;


public class PublicationYearService {
    private static final Scanner scanner = new Scanner(System.in);

    public static int getValidYear() {
        int newPublicationYear;
        while (true) {
            System.out.println("Enter publication year: ");
            if (scanner.hasNextInt()) {
                newPublicationYear = scanner.nextInt();
                scanner.nextLine();
                return newPublicationYear;
            } else {
                System.out.println("Invalid input. Please enter a valid publication year.");
                scanner.nextLine();
            }
        }
    }
}

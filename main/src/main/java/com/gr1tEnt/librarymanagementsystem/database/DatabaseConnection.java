package com.gr1tEnt.librarymanagementsystem.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static final String CONFIG_FILE = "conf/config.properties";
    private static final Properties properties = new Properties();

    static {
        try(FileInputStream inputStream = new FileInputStream(CONFIG_FILE)) {
            properties.load(inputStream);
        } catch (IOException e) {
            System.err.println("Error loading configuration file: " + e.getMessage());
        }
    }

    private static final String URL = properties.getProperty("db.url");
    private static final String USER = properties.getProperty("db.username");
    private static final String PASSWORD = properties.getProperty("db.password");

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Error while connecting to the database" + e.getMessage());
            throw new RuntimeException();
        }
    }
}

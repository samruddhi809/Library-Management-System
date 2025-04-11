package com.library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/library_demo"; // Change DB name as needed
    private static final String USER = "root"; // your MySQL username
    private static final String PASSWORD = "T#9758@qlph"; // your MySQL password

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        // Load the JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        // Establish and return the connection
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

package com.bridgelabz.addressbook.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private final static String DB_DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private final static String DB_URL = "jdbc:mysql://localhost:3306/addressbookdetails?autoReconnect=true&useSSL=false";
    private final static String DB_USERNAME = "root";
    private final static String DB_PASSWORD = "coolboy996";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connection = null;

        // load the Driver Class
        Class.forName(DB_DRIVER_CLASS);

        // create the connection now
        connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

        System.out.println("DB Connection created successfully");
        return connection;
    }
}

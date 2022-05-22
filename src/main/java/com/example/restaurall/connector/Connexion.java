package com.example.restaurall.connector;

import java.sql.*;

public class Connexion {
    private static String DRIVER = "org.sqlite.JDBC";
    public static String URL = "jdbc:sqlite:restall.db";

    public static Connection createConnexion() {
        Connection connection = null;
        try {
           Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }



}


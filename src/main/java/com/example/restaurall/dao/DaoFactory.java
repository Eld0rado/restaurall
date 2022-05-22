package com.example.restaurall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {

    private static String DRIVER = "org.sqlite.JDBC";
    private static String URL = "jdbc:sqlite:D:/_projet_sys/restaurall/restall.db";

    DaoFactory(String url) {
        this.URL = URL;
    }

    public static DaoFactory getInstance(){
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        DaoFactory instance = new DaoFactory(URL);
        return instance;
    }

    public Connection getConnexion() throws SQLException {
        Connection connexion = DriverManager.getConnection(URL);
        connexion.setAutoCommit(false);
        return connexion;
    }

    public RestaurantDao getRestaurantDao(){
        return new RestaurantDaoImpl(this);
    }

    public PlatDao getPlatDao() {
        return new PlatDaoImpl(this);
    }
    public CategDao getCategDao() {
        return new CategDaoImpl(this);
    }
}

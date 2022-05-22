package com.example.restaurall.connector;

import com.example.restaurall.beans.Restaurant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.restaurall.connector.Connexion.createConnexion;

public class RestaurantBDD {
    public static List<Restaurant> getRestaurants() throws SQLException {
        String qry = "SELECT * FROM restaurant";
        List<Restaurant> restos = new ArrayList<Restaurant>();
        Connection conn =  createConnexion();
        Statement statement = null;
        ResultSet resultSet = null;

        statement = conn.createStatement();
        resultSet = statement.executeQuery(qry);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String nom = resultSet.getString("nom");
            String adresse = resultSet.getString("adresse");
            int cp = resultSet.getInt("cp");
            String ville = resultSet.getString("ville");
            String type = resultSet.getString("type");
            restos.add(new Restaurant( id, nom, adresse, cp, ville, type));
        }
        conn.close();
        statement.close();
        resultSet.close();
        return restos;
    }
    public static void creerResto(Restaurant resto) throws SQLException {
        List<Restaurant> restaurants = getRestaurants();
        Restaurant rest = getRestoByNom(resto.getNom());
        if (!restaurants.contains(resto) || !(rest ==null)) {
            Connection connection = createConnexion();
            PreparedStatement st = connection.prepareStatement("INSERT INTO restaurant (nom, adresse, cp, ville, type) VALUES (?, ?, ?, ?, ?)");
            st.setString(1, resto.getNom());
            st.setString(2, resto.getAdresse());
            st.setInt(3, resto.getCp());
            st.setString(4, resto.getVille());
            st.setString(5, resto.getType());

            st.executeUpdate();
            st.close();
            connection.close();
        } else {
            System.out.println("Restarant déja existant");
        }

    }

    public static Restaurant getRestoByNom(String nom) throws SQLException {
        String qry = "SELECT * FROM restaurant WHERE nom ='"+nom+"'";
        Restaurant restaurant = null;

        Connection conn = createConnexion();
        Statement statement = null;
        ResultSet resultSet = null;

        if (resultSet.next()){
            do {
                int id = resultSet.getInt("id");
                String adresse = resultSet.getString("adresse");
                int cp = resultSet.getInt("cp");
                String ville = resultSet.getString("ville");
                String type = resultSet.getString("type");
                restaurant = new Restaurant(id, nom, adresse, cp, ville, type);
            }while (resultSet.next());
        }
        conn.close();
        statement.close();
        resultSet.close();
        return restaurant;
    }
    public static List<Restaurant> getRestosByType(String type) throws SQLException {
        String qry = "select * from restaurant where type = '"+type+"'";
        List<Restaurant> restos = new ArrayList<Restaurant>();
        Connection conn =  createConnexion();
        Statement statement = null;
        ResultSet resultSet = null;

        statement = conn.createStatement();
        resultSet = statement.executeQuery(qry);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String nom = resultSet.getString("nom");
            String adresse = resultSet.getString("adresse");
            int cp = resultSet.getInt("cp");
            String ville = resultSet.getString("ville");
            restos.add(new Restaurant( id, nom, adresse, cp, ville, type));
        }
        conn.close();
        statement.close();
        resultSet.close();
        return restos;
    }

    public static void main(String[] args) throws SQLException {
        System.out.println(getRestaurants());
    }
}

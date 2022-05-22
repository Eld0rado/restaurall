package com.example.restaurall.dao;

import com.example.restaurall.beans.Restaurant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RestaurantDaoImpl implements RestaurantDao {
    private DaoFactory daoFactory;

    public RestaurantDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void ajouter(Restaurant resto) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
            try {
                connexion = daoFactory.getConnexion();
                preparedStatement = connexion.prepareStatement("INSERT INTO restaurant (nom, adresse, cp, ville, type) VALUES (?, ?, ?, ?, ?)");
                preparedStatement.setString(1, resto.getNom());
                preparedStatement.setString(2, resto.getAdresse());
                preparedStatement.setInt(3, resto.getCp());
                preparedStatement.setString(4, resto.getVille());
                preparedStatement.setString(5, resto.getType());
                preparedStatement.executeUpdate();
                connexion.commit();
            } catch (SQLException throwables) {
                try {
                    if (connexion != null){
                        connexion.rollback();
                    }
                } catch (SQLException e) {
                }
                throw new DaoException("Communication avec la base de données impossible");
            }
            finally {
                try {
                    if (connexion != null){
                        connexion.close();
                    }
                } catch (SQLException throwables) {
                    throw new DaoException("Communication avec la base de données impossible");
                }
            }

    }

    @Override
    public List<Restaurant> lister() throws DaoException {
        List<Restaurant> restos = new ArrayList<Restaurant>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet result = null;
        try {
            connexion = daoFactory.getConnexion();
            statement = connexion.createStatement();
            result = statement.executeQuery("SELECT id, nom, adresse, cp, ville, type FROM restaurant");
            while (result.next()) {
                int id = result.getInt("id");
                String nom = result.getString("nom");
                String adresse = result.getString("adresse");
                int cp = result.getInt("cp");
                String ville = result.getString("ville");
                String type = result.getString("type");
                restos.add(new Restaurant(id, nom, adresse, cp, ville, type));
            }
        } catch (SQLException throwables) {
            try {
                if (connexion != null){
                     connexion.rollback();
                }
            } catch (SQLException e) {
            }
            throw new DaoException("Communication avec la base de données impossible");
        }
        finally {
            try {
                if (connexion != null){
                    connexion.close();
                }
            } catch (SQLException throwables) {
                throw new DaoException("Communication avec la base de données impossible");
            }
        }
        return restos;
    }

    public Restaurant getRestoByNom(String nom) throws DaoException {
        String qry = "SELECT * FROM restaurant WHERE nom ='"+nom+"'";
        Restaurant restaurant = new Restaurant();

        Connection connexion = null;
        Statement statement = null;
        ResultSet result = null;
        try {
            connexion = daoFactory.getConnexion();
            statement = connexion.createStatement();
            result = statement.executeQuery(qry);
            if (result.next()) {
                do {
                    int id = result.getInt("id");
                    String adresse = result.getString("adresse");
                    int cp = result.getInt("cp");
                    String ville = result.getString("ville");
                    String type = result.getString("type");
                    restaurant = new Restaurant(id, nom, adresse, cp, ville, type);
                } while (result.next());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (connexion != null){
                    connexion.close();
                }
            } catch (SQLException throwables) {
                throw new DaoException("Communication avec la base de données impossible");

            }
        }
        return restaurant;
    }

    @Override
    public Restaurant getRestoById(int id) throws DaoException {
        String qry = "SELECT * FROM restaurant WHERE id ='"+id+"'";
        Restaurant restaurant = new Restaurant();

        Connection connexion = null;
        Statement statement = null;
        ResultSet result = null;
        try {
            connexion = daoFactory.getConnexion();
            statement = connexion.createStatement();
            result = statement.executeQuery(qry);
            if (result.next()) {
                do {
                    String nom = result.getString("nom");
                    String adresse = result.getString("adresse");
                    int cp = result.getInt("cp");
                    String ville = result.getString("ville");
                    String type = result.getString("type");
                    restaurant = new Restaurant(id, nom, adresse, cp, ville, type);
                } while (result.next());
            }
        } catch (SQLException throwables) {
            throw new DaoException("Communication avec la base de données impossible");
        } finally {
            try {
                if (connexion != null){
                    connexion.close();
                }
            } catch (SQLException throwables) {
                throw new DaoException("Communication avec la base de données impossible");

            }
        }
        return restaurant;
    }

    @Override
    public void updateResto(Restaurant resto, int id) throws DaoException {
        String qry = "UPDATE restaurant set nom= ?, adresse= ?, cp= ?, ville= ?, type= ? WHERE id = '"+id+"'";
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        try {
            connexion = daoFactory.getConnexion();
            preparedStatement = connexion.prepareStatement(qry);
            preparedStatement.setString(1, resto.getNom());
            preparedStatement.setString(2, resto.getAdresse());
            preparedStatement.setInt(3, resto.getCp());
            preparedStatement.setString(4, resto.getVille());
            preparedStatement.setString(5, resto.getType());
            preparedStatement.executeUpdate();
            connexion.commit();
        } catch (SQLException throwables) {
            try {
                if (connexion != null){
                    connexion.rollback();
                }
            } catch (SQLException e) {
            }
            throw new DaoException("Communication avec la base de données impossible");
        }
        finally {
            try {
                if (connexion != null){
                    connexion.close();
                }
            } catch (SQLException throwables) {
                throw new DaoException("Communication avec la base de données impossible");
            }
        }
    }

    @Override
    public List<Restaurant> getRestosByType(String type) throws DaoException {
        String qry = "select * from restaurant where type = '"+type+"'";
        List<Restaurant> restos = new ArrayList<Restaurant>();
        Connection connexion =  null;
        Statement statement = null;
        ResultSet result = null;

        try {
            connexion = daoFactory.getConnexion();
            statement = connexion.createStatement();
            result = statement.executeQuery(qry);

        while (result.next()) {
            int id = result.getInt("id");
            String nom = result.getString("nom");
            String adresse = result.getString("adresse");
            int cp = result.getInt("cp");
            String ville = result.getString("ville");
            restos.add(new Restaurant( id, nom, adresse, cp, ville, type));
        }
        } catch (SQLException throwables) {
            throw new DaoException("Communication avec la base de données impossible");
        } finally {
            try {
                if (connexion != null) {
                    connexion.close();
                }
            } catch (SQLException throwables) {
                throw new DaoException("Communication avec la base de données impossible");

            }
        }
        return restos;
    }

}
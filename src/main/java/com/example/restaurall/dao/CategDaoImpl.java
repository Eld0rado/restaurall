package com.example.restaurall.dao;

import com.example.restaurall.beans.Categ;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategDaoImpl implements CategDao {
    private DaoFactory daoFactory;

    public CategDaoImpl(DaoFactory daoFactory){
        this.daoFactory = daoFactory;
    }

    @Override
    public List<Categ> lister() throws DaoException {
        List<Categ> categs = new ArrayList<Categ>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet result = null;
        try {
            connexion = daoFactory.getConnexion();
            statement = connexion.createStatement();
            result = statement.executeQuery("SELECT id, nom FROM plat");
            while (result.next()) {
                int id = result.getInt("id");
                String nom = result.getString("nom");
                categs.add(new Categ(id, nom));

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
        return categs;
    }

    @Override
    public void ajouter(Categ categ) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        try {
            connexion = daoFactory.getConnexion();
            preparedStatement = connexion.prepareStatement("INSERT INTO plat (nom) VALUES ( ?)");
            preparedStatement.setString(1, categ.getNom());
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
    public Categ getPlatById(int id) throws DaoException {
        String qry = "SELECT * FROM plat WHERE id ='"+id+"'";
        Categ categ = new Categ();
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
                    categ = new Categ(nom);
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
        return categ;
    }
}

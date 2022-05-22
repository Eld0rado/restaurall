package com.example.restaurall.dao;

import com.example.restaurall.beans.Plat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implémentations de l'interface PlatDao
 * Création des méthodes et traitements sql
 */
public class PlatDaoImpl implements PlatDao {
    private DaoFactory daoFactory;

    public PlatDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    /**
     * @param idResto
     * @return Liste des PLats d'un resto
     * @throws DaoException
     */
    @Override
    public List<Plat> lister(int idResto) throws DaoException {
        List<Plat> lprixes = new ArrayList<Plat>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet result = null;
        try {
            connexion = daoFactory.getConnexion();
            statement = connexion.createStatement();
            result = statement.executeQuery("SELECT idResto, idPlat, nom, prix, type FROM plat WHERE idResto ='" + idResto + "'");
            while (result.next()) {
                int idP = result.getInt("idPlat");
                String nom = result.getString("nom");
                double prix = result.getDouble("prix");
                String type = result.getString("type");
                lprixes.add(new Plat(idResto, idP, nom, prix, type));
            }
        } catch (SQLException throwables) {
            try {
                if (connexion != null) {
                    connexion.rollback();
                }
            } catch (SQLException e) {
            }
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
        return lprixes;
    }

    /**
     * Insertion d'un plat en bdd
     *
     * @param plat
     * @param idResto
     * @throws DaoException
     */
    @Override
    public void ajouter(Plat plat, int idResto) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        try {
            connexion = daoFactory.getConnexion();
            preparedStatement = connexion.prepareStatement("INSERT INTO plat (idResto, nom, prix, type) VALUES ( ?, ?, ?, ?)");
            preparedStatement.setInt(1, idResto);
            preparedStatement.setString(2, plat.getNom());
            preparedStatement.setDouble(3, plat.getPrix());
            preparedStatement.setString(4, plat.getType());
            preparedStatement.executeUpdate();
            connexion.commit();

        } catch (SQLException throwables) {
            try {
                if (connexion != null) {
                    connexion.rollback();
                }
            } catch (SQLException e) {
            }
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
    }

    /**
     * Mise a jour des champs d'un plat
     *
     * @param plat
     * @throws DaoException
     */
    @Override
    public void update(Plat plat) throws DaoException {
        String qry = "UPDATE plat SET nom= ?, prix= ?, type= ? WHERE idPlat = ?";
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        try {
            connexion = daoFactory.getConnexion();
            preparedStatement = connexion.prepareStatement(qry);
            preparedStatement.setString(1, plat.getNom());
            preparedStatement.setDouble(2, plat.getPrix());
            preparedStatement.setString(3, plat.getType());
            preparedStatement.setInt(4, plat.getIdPlat());
            preparedStatement.executeUpdate();
            connexion.commit();
        } catch (SQLException throwables) {
            try {
                if (connexion != null) {
                    connexion.rollback();
                }
            } catch (SQLException e) {
            }
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
    }

    /**
     * Return un plat par son Id
     *
     * @param id
     * @return
     * @throws DaoException
     */
    @Override
    public Plat getPlatById(int id) throws DaoException {

        String qry = "SELECT * FROM plat WHERE idPlat ='" + id + "'";
        Plat plat = new Plat();
        Connection connexion = null;
        Statement statement = null;
        ResultSet result = null;
        try {
            connexion = daoFactory.getConnexion();
            statement = connexion.createStatement();
            result = statement.executeQuery(qry);
            if (result.next()) {
                do {
                    int idResto = result.getInt("idResto");
                    int idPlat = result.getInt("idPlat");
                    String nom = result.getString("nom");
                    Double prix = result.getDouble("prix");
                    String type = result.getString("type");
                    plat = new Plat(idResto, idPlat, nom, prix, type);
                } while (result.next());
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
        return plat;
    }

    /**
     * Suppression d'un plat de la dbb
     *
     * @param id
     * @throws DaoException
     */
    @Override
    public void delete(int id) throws DaoException {
        boolean rowDeleted;
        String qry = "DELETE FROM plat WHERE idPlat =" + id;
        Connection connexion = null;
        Statement statement = null;
        // boolean result;
        try {
            connexion = daoFactory.getConnexion();
            statement = connexion.createStatement();

            // statement.setInt(1, id);
            statement.executeUpdate(qry);
            connexion.commit();
        } catch (SQLException throwables) {
            throw new DaoException("Communication avec la base de données impossible" + qry);
        } finally {
            try {
                if (connexion != null) {
                    connexion.close();
                }
            } catch (SQLException throwables) {
                throw new DaoException("Communication avec la base de données impossible" + qry);

            }
        }
    }
}

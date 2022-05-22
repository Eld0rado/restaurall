package com.example.restaurall.dao;

import com.example.restaurall.beans.Plat;

import java.util.List;

public interface PlatDao {
    List<Plat> lister(int idReto) throws DaoException;
    void ajouter(Plat plat, int idResto) throws DaoException;
    void update(Plat plat) throws DaoException;
    Plat getPlatById(int id) throws DaoException;
    void delete(int id) throws DaoException;
}

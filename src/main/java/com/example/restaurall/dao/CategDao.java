package com.example.restaurall.dao;

import com.example.restaurall.beans.Categ;

import java.util.List;

public interface CategDao {
    List<Categ> lister() throws DaoException;
    void ajouter(Categ categ) throws DaoException;
    Categ getPlatById(int id) throws DaoException;
}

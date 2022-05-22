package com.example.restaurall.dao;

import com.example.restaurall.beans.Restaurant;

import java.util.List;

public interface RestaurantDao {
    void ajouter(Restaurant resto) throws DaoException;
    List<Restaurant> lister() throws DaoException;
    Restaurant getRestoByNom(String nom) throws DaoException;
    Restaurant getRestoById(int id) throws DaoException;
    void updateResto(Restaurant resto, int id) throws DaoException;
    List<Restaurant> getRestosByType(String type) throws DaoException;
}

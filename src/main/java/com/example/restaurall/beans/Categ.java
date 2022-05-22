package com.example.restaurall.beans;

import java.io.Serializable;

public class Categ implements Serializable {
    private static final long serialVersionUID = -6693179704724564043L;

    private int id;
    private String nom;

    public Categ(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Categ() {

    }

    public Categ(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    @Override
    public String toString() {
        return "Categ{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}

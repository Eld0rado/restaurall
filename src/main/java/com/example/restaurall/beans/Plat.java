package com.example.restaurall.beans;

import java.io.Serializable;

public class Plat implements Serializable {
    private static final long serialVersionUID = -4862910225147703502L;

    private int idResto;
    private int idPlat;
    private String nom;
    private double prix;

    private String type;
    public Plat() {
    }
    public Plat(int idPlat, int idResto, String nom, double prix) {
        this.idPlat = idPlat;
        this.idResto = idResto;
        this.nom = nom;
        this.prix = prix;
    }

    public Plat(int idResto, int idPlat, String nom, double prix, String type) {
        this.idResto = idResto;
        this.idPlat = idPlat;
        this.nom = nom;
        this.prix = prix;
        this.type = type;
    }

    public int getIdResto() {
        return idResto;
    }

    public void setIdResto(int idResto) {
        this.idResto = idResto;
    }

    public int getIdPlat() {
        return idPlat;
    }

    public void setIdPlat(int idPlat) {
        this.idPlat = idPlat;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Plat{" +
                "idResto=" + idResto +
                ", idPlat=" + idPlat +
                ", nom='" + nom + '\'' +
                ", prix=" + prix +
                '}';
    }
}

package com.example.restaurall.beans;

import java.io.Serializable;

/**
 * Class modele des Restaurants
 */
public class Restaurant implements Serializable {
    private static final long serialVersionUID = 2151293318286497112L;

    private int id;
    private String nom;
    private String adresse;
    private int cp;
    private String ville;
    private String type;

    public Restaurant(String nom, String adresse, int cp, String ville, String type) {
        this.nom = nom;
        this.adresse = adresse;
        this.cp = cp;
        this.ville = ville;
        this.type = type;
    }

    public Restaurant(int id, String nom, String adresse, int cp, String ville, String type) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.cp = cp;
        this.ville = ville;
        this.type = type;
    }

    public Restaurant() {

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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", adresse='" + adresse + " " +
                cp + " " + ville + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}

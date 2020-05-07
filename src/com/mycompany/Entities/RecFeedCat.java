
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entities;

/**
 *
 * @author Chédy
 */
public class RecFeedCat {
    int id_cat;
    String nom;
    String image;

    public RecFeedCat() {
    }

    public RecFeedCat(int id_cat, String nom) {
        this.id_cat = id_cat;
        this.nom = nom;
    }
    
    
    

    public RecFeedCat(int id_cat, String nom, String image) {
        this.id_cat = id_cat;
        this.nom = nom;
        this.image = image;
    }

    public RecFeedCat(String nom, String image) {
        this.nom = nom;
        this.image = image;
    }

    public int getId_cat() {
        return id_cat;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    
    
}

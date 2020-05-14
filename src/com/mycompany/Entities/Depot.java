/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entities;


public class Depot {
    
    private int id ;
    private String adresse;
    private int surface ;
    private int prix;
    private String etat;

    public Depot() {
    }
    
    

    public Depot(String adresse, int surface, int prix, String etat) {
        this.adresse = adresse;
        this.surface = surface;
        this.prix = prix;
        this.etat = etat;
    }
    
    

    public Depot(int id, String adresse, int surface, int prix, String etat) {
        this.id = id;
        this.adresse = adresse;
        this.surface = surface;
        this.prix = prix;
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getSurface() {
        return surface;
    }

    public void setSurface(int surface) {
        this.surface = surface;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Depot{" + "id=" + id + ", adresse=" + adresse + ", surface=" + surface + ", prix=" + prix + ", etat=" + etat + '}';
    }
    
    
    
}

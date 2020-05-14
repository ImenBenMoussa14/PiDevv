/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entities;

import java.util.Date;


public class Location {
    
    private int id;
 
    private String date_debut;
    private String date_fin;
    private String etat;
    
    private Depot depot;
    private Utilisateur user;

    public Location() {
    }

    public Location(String date_debut, String date_fin, String etat, Depot depot, Utilisateur user) {
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.etat = etat;
        this.depot = depot;
        this.user = user;
    }

    public Location(int id, String date_debut, String date_fin, String etat, Depot depot, Utilisateur user) {
        this.id = id;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.etat = etat;
        this.depot = depot;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Depot getDepot() {
        return depot;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "Location{" + "id=" + id  + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", etat=" + etat + '}';
    }
    
    
    
}

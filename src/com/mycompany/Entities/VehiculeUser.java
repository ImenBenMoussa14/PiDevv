/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entities;

import java.util.Date;

/**
 *
 * @author Maryem
 */
public class VehiculeUser {
    private int id;
    private String matricule;
    private int id_user ;
    private Date date_debut;
    private Date date_fin;
    private String date_debut1;
    private String date_fin1;

    public String getDate_debut1() {
        return date_debut1;
    }

    public void setDate_debut1(String date_debut1) {
        this.date_debut1 = date_debut1;
    }

    public String getDate_fin1() {
        return date_fin1;
    }

    public void setDate_fin1(String date_fin1) {
        this.date_fin1 = date_fin1;
    }
    
    
     private String etat;

    public VehiculeUser() {
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "VehiculeUser{" + "id=" + id + ", matricule=" + matricule + ", id_user=" + id_user + ", date_debut=" + date_debut1 + ", date_fin=" + date_fin1 + ", etat=" + etat + '}';
    } 
            
}

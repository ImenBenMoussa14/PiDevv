/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entities;
/**
 *
 * @author Maryem
 */
import java.util.Objects;

public class Vehicule {
 
     private String mat;
     private String type;
     private String puissance;
     private String marque;
     public String kilometrages;
     private String nbPlaces;
     private String etat;
     private String prix;
     private String photo;
     private String couleur;

    public Vehicule(String mat, String type, String puissance, String marque, String kilometrages, String nbPlaces, String etat, String prix, String photo, String couleur) {
        this.mat = mat;
        this.type = type;
        this.puissance = puissance;
        this.marque = marque;
        this.kilometrages = kilometrages;
        this.nbPlaces = nbPlaces;
        this.etat = etat;
        this.prix = prix;
        this.photo = photo;
        this.couleur = couleur;
    }

    public String getMat() {
        return mat;
    }

    public void setMat(String mat) {
        this.mat = mat;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPuissance() {
        return puissance;
    }

    public void setPuissance(String puissance) {
        this.puissance = puissance;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getKilometrages() {
        return kilometrages;
    }

    public void setKilometrages(String kilometrages) {
        this.kilometrages = kilometrages;
    }

    public String getNbPlaces() {
        return nbPlaces;
    }

    public void setNbPlaces(String nbPlaces) {
        this.nbPlaces = nbPlaces;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    @Override
    public String toString() {
        return "Vehicule{" + "mat=" + mat + ", type=" + type + ", puissance=" + puissance + ", marque=" + marque + ", kilometrages=" + kilometrages + ", nbPlaces=" + nbPlaces + ", etat=" + etat + ", prix=" + prix + ", photo=" + photo + ", couleur=" + couleur + '}';
    }

    public Vehicule() {
    }
   
    
  
   
   

   
}

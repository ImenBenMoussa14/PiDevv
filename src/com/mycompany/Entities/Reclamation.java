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
public class Reclamation {
     private int id_rec;
    private String objet;
    private String description;
    private int etat;
    private String date;
    private String image;
    private int iduser;
    private int id_cat;
    private String webPath;
    
    public Reclamation() {
    }

    public String getWebPath() {
        return webPath;
    }

    public void setWebPath(String webPath) {
        this.webPath = webPath;
    }

    public Reclamation(String objet, String description, int etat, String date, int iduser, int id_cat, String webPath) {
        this.objet = objet;
        this.description = description;
        this.etat = etat;
        this.date = date;
        this.iduser = iduser;
        this.id_cat = id_cat;
        this.webPath = webPath;
    }

    public Reclamation(String objet, String description, int etat, String date, String image, int iduser) {
        this.objet = objet;
        this.description = description;
        this.etat = etat;
        this.date = date;
        this.image = image;
        this.iduser = iduser;
    }
    
    

    public Reclamation(String objet, String description, int etat, String date, String image, int iduser,int cat) {
        this.objet = objet;
        this.description = description;
        this.etat = etat;
        this.date = date;
        this.image = image;
        this.iduser = iduser;
        this.id_cat=id_cat;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id_rec=" + id_rec + ", objet=" + objet + ", description=" + description + ", etat=" + etat + ", date=" + date + ", image=" + image + ", iduser=" + iduser + ", cat=" + id_cat + '}';
    }

    
    public int getId_rec() {
        return id_rec;
    }

    public void setId_rec(int id_rec) {
        this.id_rec = id_rec;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getCat() {
        return id_cat;
    }

    public void setCat(int id_cat) {
        this.id_cat = id_cat;
    }

    
    
    public Reclamation(int id_rec, String objet, String description, int etat, String date, String image, int iduser, int id_cat) {
        this.id_rec = id_rec;
        this.objet = objet;
        this.description = description;
        this.etat = etat;
        this.date = date;
        this.image = image;
        this.iduser = iduser;
        this.id_cat = id_cat;
    }

    public Reclamation(String objet, String description) {
        this.objet = objet;
        this.description = description;
    }

   
    
    
}

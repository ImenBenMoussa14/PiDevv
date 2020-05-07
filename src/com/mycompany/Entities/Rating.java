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
public class Rating {
    private int id;
    private int iduser;
    private int note;
    private int idlivreur;

    public Rating(int iduser, int note, int idlivreur) {
        this.iduser = iduser;
        this.note = note;
        this.idlivreur = idlivreur;
    }

    public Rating(int id, int iduser, int note, int idlivreur) {
        this.id = id;
        this.iduser = iduser;
        this.note = note;
        this.idlivreur = idlivreur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public int getIdlivreur() {
        return idlivreur;
    }

    public void setIdlivreur(int idlivreur) {
        this.idlivreur = idlivreur;
    }
    
    
    
    
}

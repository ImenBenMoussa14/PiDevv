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
public class Feedback {
    
    private int iduser;
    private int id_feed;
    private String description;
    private int note;
    private String image;
    private String datefeedback;

    public Feedback(int iduser, String description, int note, String image, String datefeedback) {
        this.iduser = iduser;
        this.description = description;
        this.note = note;
        this.image = image;
        this.datefeedback = datefeedback;
    }

    public Feedback(int iduser, int id_feed, String description, int note, String image, String datefeedback) {
        this.iduser = iduser;
        this.id_feed = id_feed;
        this.description = description;
        this.note = note;
        this.image = image;
        this.datefeedback = datefeedback;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getId_feed() {
        return id_feed;
    }

    public void setId_feed(int id_feed) {
        this.id_feed = id_feed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDatefeedback() {
        return datefeedback;
    }

    public void setDatefeedback(String datefeedback) {
        this.datefeedback = datefeedback;
    }

    @Override
    public String toString() {
        return "Feedback{" + "iduser=" + iduser + ", id_feed=" + id_feed + ", description=" + description + ", note=" + note + ", image=" + image + ", datefeedback=" + datefeedback + '}';
    }
    
    
   
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entities;


public class DepotAdresse {
    
     private int num ;
    private String adresse;

    public DepotAdresse() {
    }

    public DepotAdresse(int num, String adresse) {
        this.num = num;
        this.adresse = adresse;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "DepotAdresse{" + "num=" + num + ", adresse=" + adresse + '}';
    }
    
    
}

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
public class Employe {
    
    private int ID_emp;
    private String USERNAME,PRENOM,EMAIL,ADRESSE;
    private String DATE_NAISSANCE;
    private String CIN;
    private String ROLE,MISSION;
    private int telephone;
    private String governat;
    private String password;
    private String disponible;
    private int id;

    public Employe(int ID_emp, String USERNAME, String PRENOM, String EMAIL, String ADRESSE, String DATE_NAISSANCE, String CIN, String ROLE, String MISSION, int telephone, String governat, String password, String disponible, int id) {
        this.ID_emp = ID_emp;
        this.USERNAME = USERNAME;
        this.PRENOM = PRENOM;
        this.EMAIL = EMAIL;
        this.ADRESSE = ADRESSE;
        this.DATE_NAISSANCE = DATE_NAISSANCE;
        this.CIN = CIN;
        this.ROLE = ROLE;
        this.MISSION = MISSION;
        this.telephone = telephone;
        this.governat = governat;
        this.password = password;
        this.disponible = disponible;
        this.id = id;
    }

    public Employe(String USERNAME, String PRENOM, String EMAIL, String ADRESSE, String DATE_NAISSANCE, String CIN, String ROLE, String MISSION, int telephone, String governat, String password, String disponible, int id) {
        this.USERNAME = USERNAME;
        this.PRENOM = PRENOM;
        this.EMAIL = EMAIL;
        this.ADRESSE = ADRESSE;
        this.DATE_NAISSANCE = DATE_NAISSANCE;
        this.CIN = CIN;
        this.ROLE = ROLE;
        this.MISSION = MISSION;
        this.telephone = telephone;
        this.governat = governat;
        this.password = password;
        this.disponible = disponible;
        this.id = id;
    }

    public int getID_emp() {
        return ID_emp;
    }

    public void setID_emp(int ID_emp) {
        this.ID_emp = ID_emp;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getPRENOM() {
        return PRENOM;
    }

    public Employe(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    
    
    public void setPRENOM(String PRENOM) {
        this.PRENOM = PRENOM;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getADRESSE() {
        return ADRESSE;
    }

    public void setADRESSE(String ADRESSE) {
        this.ADRESSE = ADRESSE;
    }

    public String getDATE_NAISSANCE() {
        return DATE_NAISSANCE;
    }

    public void setDATE_NAISSANCE(String DATE_NAISSANCE) {
        this.DATE_NAISSANCE = DATE_NAISSANCE;
    }

    public String getCIN() {
        return CIN;
    }

    public void setCIN(String CIN) {
        this.CIN = CIN;
    }

    public String getROLE() {
        return ROLE;
    }

    public void setROLE(String ROLE) {
        this.ROLE = ROLE;
    }

    public String getMISSION() {
        return MISSION;
    }

    public void setMISSION(String MISSION) {
        this.MISSION = MISSION;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getGovernat() {
        return governat;
    }

    public void setGovernat(String governat) {
        this.governat = governat;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisponible() {
        return disponible;
    }

    public void setDisponible(String disponible) {
        this.disponible = disponible;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employe() {
    }

    public Employe(int ID_emp, String USERNAME, String EMAIL, String MISSION) {
        this.ID_emp = ID_emp;
        this.USERNAME = USERNAME;
        this.EMAIL = EMAIL;
        this.MISSION = MISSION;
    }
    
    
    
    
    
    
}

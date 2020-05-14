/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entities;


/**
 *
 * @author amall
 */
public class LignePanier {
    private int id_pro;
    private String libelle;
    private int prix;
    private int quantite=1;
    private String photo;
    private int stock;

    public LignePanier() {
    }

    public LignePanier(int id_pro, String libelle, int prix) {
        this.id_pro = id_pro;
        this.libelle = libelle;
        this.prix = prix;
    }
   public LignePanier(int id_pro, String libelle, int prix, String photo) {
        this.id_pro = id_pro;
        this.libelle = libelle;
        this.prix = prix;
        this.photo = photo;
    }

    public LignePanier(int id_pro, String libelle, int prix, String photo, int stock) {
        this.id_pro = id_pro;
        this.libelle = libelle;
        this.prix = prix;
        this.photo = photo;
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
   
    public int getId_pro() {
        return id_pro;
    }

    public String getLibelle() {
        return libelle;
    }

    public int getPrix() {
        return prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public String getPhoto() {
        return photo;
    }
    

    public void setId_produit(int id_pro) {
        this.id_pro = id_pro;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
    @Override
    public int hashCode() {
      return id_pro;
    }
    
    @Override
    public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      LignePanier other = (LignePanier) obj;
      if (this.id_pro != other.id_pro)
         return false;
      return true;
    }
 //    @Override
   // public String toString() {
     //   return new String( "LignePanier{" + "id_pro=" + id_pro + ", prix=" + String.valueOf(prix) + ", nom_pd=" + libelle + ", Quantite=" + quantite + ", photo=" + photo + '}');
    //}

    @Override
    public String toString() {
        return "LignePanier{" + "id_pro=" + id_pro + ", libelle=" + libelle + ", prix=" + prix + ", quantite=" + quantite + ", photo=" + photo + ", stock=" + stock + '}';
    }

}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Service;
import com.mycompany.utils.Statics;
import com.mycompany.Entities.Produit;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author amall
 */
public class ServiceProduit {

    public ArrayList<Produit> produits;
    public static ServiceProduit instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceProduit() {
         req = new ConnectionRequest();
    }

    public static ServiceProduit getInstance() {
        if (instance == null) {
            instance = new ServiceProduit();
        }
        return instance;
    }

    public ArrayList<Produit> parseProduits(String jsonText){
        try {
            produits=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> produitsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)produitsListJson.get("root");
            for(Map<String,Object> obj : list){
                Produit p = new Produit();
                
                float id_pro= Float.parseFloat(obj.get("id").toString());
                p.setId_pro((int)id_pro);
                float poids= Float.parseFloat(obj.get("poids").toString());
                p.setPoids((int)poids);
                float prix= Float.parseFloat(obj.get("prix").toString());
                p.setPrix((int)prix);
//             float categorie= Float.parseFloat(obj.get("idcat").toString());
          //   p.setCategorie((int)categorie);
                 
             float id_depot= Float.parseFloat(obj.get("idDepot").toString());
              p.setId_depot((int)id_depot);
                float Quantite= Float.parseFloat(obj.get("quantite").toString());
                p.setQuantite((int)Quantite);
             p.setPhoto(obj.get("photo").toString());
                p.setDescription(obj.get("desription").toString());
                p.setNom_pd(obj.get("libelle").toString());
                
               p.setEtat(obj.get("etat").toString());
             
                produits.add(p);
                System.out.println(produits);
            }
            
            
        } catch (IOException ex) {
                            System.out.println("error related to sql");
        }
        return produits;
    }
    //needs fixing!!!!
        public ArrayList<Produit> parsesearchProduits(String jsonText, String entry){
        try {
            produits=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> produitsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)produitsListJson.get("root");
            for(Map<String,Object> obj : list){
                Produit p = new Produit();
                
                float id_pro= Float.parseFloat(obj.get("id").toString());
                p.setId_pro((int)id_pro);
                float poids= Float.parseFloat(obj.get("poids").toString());
                p.setPoids((int)poids);
                float prix= Float.parseFloat(obj.get("prix").toString());
                p.setPrix((int)prix);
//             float categorie= Float.parseFloat(obj.get("idcat").toString());
          //   p.setCategorie((int)categorie);
                 
             float id_depot= Float.parseFloat(obj.get("idDepot").toString());
              p.setId_depot((int)id_depot);
                float Quantite= Float.parseFloat(obj.get("quantite").toString());
                p.setQuantite((int)Quantite);
             p.setPhoto(obj.get("photo").toString());
                p.setDescription(obj.get("desription").toString());
                p.setNom_pd(obj.get("libelle").toString());
               p.setEtat(obj.get("etat").toString());
               System.out.println("what is the entry?"+entry);
             if(entry.equals(p.getNom_pd())){
                produits.add(p);
                System.out.println(produits);
            }}
            
            
        } catch (IOException ex) {
                            System.out.println("error related to sql");
        }
        return produits;
    }
    public ArrayList<Produit> getAllProduits(){
        String url = Statics.BASE_URL+"/produitVitrine/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                produits = parseProduits(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return produits;
        

    }
    //needs fixing!!! 
        public ArrayList<Produit> getSearchedProduits(){
        final String en=Vitrine.entry;
        String url = Statics.BASE_URL+"/produitVitrine/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                produits = parsesearchProduits(new String(req.getResponseData()),en);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return produits;
        

    }
 public Produit DetailProd(int id_pro, Produit p) {

        ConnectionRequest con = new ConnectionRequest();
        String Url = Statics.BASE_URL+"/produits/detailProduit/" + id_pro;
        con.setUrl(Url);
        con.addResponseListener((NetworkEvent e) -> {
            JSONParser jsonp = new JSONParser();
            try {
                Map<String, Object> obj = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                p.setNom_pd(obj.get("libelle").toString());
                p.setPrix(Integer.parseInt(obj.get("prix").toString()));
                p.setDescription(obj.get("description").toString());
                //p.setCategorie(obj.get("").toString());
               

            } catch (IOException ex) {
                System.out.println("error related to sql");
            }
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return null;
    }

   
}
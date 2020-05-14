/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Service;

import com.mycompany.Entities.Depot;
import com.mycompany.Entities.DepotAdresse;
import com.mycompany.Utils.Statics;
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
 * @author houss
 */
public class DepotService {
    
    
    public ArrayList<Depot> depots;   
    public ArrayList<DepotAdresse> depotsAdresse;
    

    
    public static DepotService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private DepotService() {
         req = new ConnectionRequest();
    }

    public static DepotService getInstance() {
        if (instance == null) {
            instance = new DepotService();
        }
        return instance;
    }

    public boolean addDepot(Depot d) {
        String url = Statics.BASE_URL + "depot/new?adresse="+d.getAdresse()+"&surface="+d.getSurface()+"&prix="+d.getPrix()+"&etat="+d.getEtat() ;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    public boolean modifierDepot(Depot d) {
        String url = Statics.BASE_URL + "depot/modifier?adresse="+d.getAdresse()+"&surface="+d.getSurface()+"&prix="+d.getPrix()+"&etat="+d.getEtat()+"&id="+d.getId() ;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
      public boolean deleteDepot(int id) {
        String url = Statics.BASE_URL + "depot/delete/"+id ;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    
    
    public ArrayList<Depot> parseDepots(String jsonText){
        try {
            depots=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                
                Depot d = new Depot();
                
                float id = Float.parseFloat(obj.get("id").toString());
                d.setId((int)id);
                d.setAdresse(obj.get("adresse").toString());
                d.setPrix(((int)Float.parseFloat(obj.get("prix").toString())));
                d.setSurface(((int)Float.parseFloat(obj.get("surface").toString())));
                d.setEtat(obj.get("etat").toString());
               
                
                depots.add(d);
            }
            
            
        } catch (IOException ex) {
            
        }
        return depots;
    }
    
     public ArrayList<DepotAdresse> parseDepotsAdresse(String jsonText){
        try {
            depotsAdresse=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                
                  DepotAdresse d = new DepotAdresse();
                
                float num = Float.parseFloat(obj.get("num").toString());
                d.setNum((int)num);
                d.setAdresse(obj.get("adresse").toString());
                
                depotsAdresse.add(d);
              
            }
            
            
        } catch (IOException ex) {
            
        }
        return depotsAdresse;
    }
    
    public ArrayList<Depot> getAllDepots(){
        String url = Statics.BASE_URL+"depot/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                depots = parseDepots(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return depots;
    }
    
     public ArrayList<DepotAdresse> getAllAdresse(){
        String url = Statics.BASE_URL+"depot/all/adresse";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                depotsAdresse = parseDepotsAdresse(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return depotsAdresse;
    }
    
     public ArrayList<Depot> chercherDepotsParAdresse(String adresse){
        String url = Statics.BASE_URL+"depot/chercher/"+adresse;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                depots = parseDepots(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return depots;
    }
    
}

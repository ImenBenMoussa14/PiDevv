/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Service;

import com.mycompany.Entities.Depot;
import com.mycompany.Entities.Location;
import com.mycompany.Entities.Utilisateur;
import com.mycompany.Utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class LocationService {
    
    
    public ArrayList<Location> locations;
    
    public static LocationService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private LocationService() {
         req = new ConnectionRequest();
    }

    public static LocationService getInstance() {
        if (instance == null) {
            instance = new LocationService();
        }
        return instance;
    }

       public boolean addLocation(Location l) {
        String url = Statics.BASE_URL + "location/new?depot="+l.getDepot().getId()+"&user="+l.getUser().getId()+"&etat="+l.getEtat()+"&date_debut="+l.getDate_debut()+"&date_fin="+l.getDate_fin() ;
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
       
          public boolean modifierLocation(Location l) {
        String url = Statics.BASE_URL + "location/modifier?id="+l.getId()+"&etat="+l.getEtat()+"&date_debut="+l.getDate_debut()+"&date_fin="+l.getDate_fin() ;
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
    
     public boolean deleteLocation(int id) {
        String url = Statics.BASE_URL + "location/delete/"+id ;
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
    
    public ArrayList<Location> parseLocations(String jsonText){
        try {
            locations=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                
                Location l = new Location();
                
                float id = Float.parseFloat(obj.get("id").toString());
                l.setId((int)id);
                Utilisateur u = new Utilisateur((int) Float.parseFloat(obj.get("user").toString()));
                l.setUser(u);
                l.setDate_debut(obj.get("datedebut").toString());
                l.setDate_fin(obj.get("datefin").toString());
                l.setEtat(obj.get("etat").toString());
               
               
                      Map<String,Object> objDepot = (Map<String,Object>) obj.get("depot");
              
                  Depot d = new Depot();
                
                float idd = Float.parseFloat(objDepot.get("id").toString());
                d.setId((int)idd);
                d.setAdresse(objDepot.get("adresse").toString());
                d.setPrix(((int)Float.parseFloat(objDepot.get("prix").toString())));
                d.setSurface(((int)Float.parseFloat(objDepot.get("surface").toString())));
                d.setEtat(objDepot.get("etat").toString());
               
                l.setDepot(d);
            
                locations.add(l);
            }
            
            
        } catch (IOException ex) {
            
        }
        return locations;
    }
    
    public ArrayList<Location> getAllLocations(){
        String url = Statics.BASE_URL+"location/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                locations = parseLocations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return locations;
    }
    
}

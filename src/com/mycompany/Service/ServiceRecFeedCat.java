/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.mycompany.Entities.RecFeedCat;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Chédy
 */
public class ServiceRecFeedCat {
    
    
     public static ServiceRecFeedCat instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceRecFeedCat() {
         req = new ConnectionRequest();
    }

    public static ServiceRecFeedCat getInstance() {
        if (instance == null) {
            instance = new ServiceRecFeedCat();
        }
        return instance;
    }
    
    
    public ArrayList<RecFeedCat> getAllRecFeedCat() {
        ArrayList<RecFeedCat> listCat = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/ProjetWebSymfony/test/web/app_dev.php/api/recfeedcat/all");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> cat
                            = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(cat);
                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) cat.get("root");
                    for (Map<String, Object> obj : list) {
                        RecFeedCat recFeedcat = new RecFeedCat();
                        float id = Float.parseFloat(obj.get("idCat").toString());
                        String nom = obj.get("nom").toString();
                        String image = obj.get("image").toString();
                        
                        recFeedcat.setId_cat((int)id);
                        recFeedcat.setNom(nom);
                        recFeedcat.setImage(image);
                        listCat.add(recFeedcat);
                    }
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
            });
        
     
                NetworkManager.getInstance().addToQueueAndWait(con);
    return listCat;
    }
    
  
    
            public static RecFeedCat RecFeedByID(int id){
        String id_string = String.valueOf(id);
        Map<String, Object> top;
        RecFeedCat recfeed = new RecFeedCat();
        String url = "http://localhost/ProjetWebSymfony/test/web/app_dev.php/api/recfeed/byid?id_cat="+recfeed.getId_cat();
        ConnectionRequest request = new ConnectionRequest(url,false);
        request.addArgument("idCat", id_string);
        NetworkManager.getInstance().addToQueueAndWait(request);
        
                JSONParser j = new JSONParser();
                String json = new String(request.getResponseData()) + "";
                if (!json.equals("no data")){                            
            try {
                top = j.parseJSON(new CharArrayReader(json.toCharArray()));
                                    
            
                                                              
                                    recfeed = new RecFeedCat((int)Float.parseFloat(top.get("idCat").toString()),
                                            top.get("nom").toString()                                            
                                    );
                                    
                                    
                                  
                                    
                            } catch (IOException ex) {
                ex.printStackTrace();
            }
                        
                                       
    }
    return recfeed;
    }
            
            
            public static void editRecFeedCat(RecFeedCat e, String path){
        String url = "http://localhost/ProjetWebSymfony/test/web/app_dev.php/api/editCat";
                MultipartRequest req = new MultipartRequest();
                req.setUrl(url);
                req.setPost(true);
                req.addArgument("id_cat", String.valueOf(e.getId_cat()));
                req.addArgument("nom", e.getNom());
                              if(!path.equals("")){
                                  try{
                    req.addData("image", path,"image/jpeg");
                    req.setFilename("image", e.getNom()+".jpg");
                }catch(Exception exx) {
                    exx.printStackTrace();
                }
                              }
                req.addResponseListener((response)-> {
                    
                    byte[] data = (byte[]) response.getMetaData();
                    String s = new String(data);
                    System.out.println(s);
                    if (s.equals("success")) {
                           Dialog.show("Succès", "Categorie reclamation/feedback modifié avec succès", "Ok", null);
                            System.out.println("new data = "+data);
                    } else {
                        Dialog.show("Erreur", "Echec de modifier", "Ok", null);
                    }                    
                });
                NetworkManager.getInstance().addToQueueAndWait(req);
    }
    

            
            
}
    
   


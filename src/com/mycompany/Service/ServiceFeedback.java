/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.Entities.Employe;
import com.mycompany.Entities.Feedback;
import com.mycompany.Entities.Rating;
import com.mycompany.Entities.Reclamation;
import com.mycompany.gui.SessionManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;

/**
 *
 * @author Chédy
 */
public class ServiceFeedback {
         int  nbrAfterConvert;
          String nbrBeforeConvert;
    
      public ArrayList<Feedback> reclamations;
    
    public static ServiceFeedback instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceFeedback() {
         req = new ConnectionRequest();
    }

    public static ServiceFeedback getInstance() {
        if (instance == null) {
            instance = new ServiceFeedback();
        }
        return instance;
    }

     
     public ArrayList<Feedback> getFeedbacks() 
     {
        ArrayList<Feedback> listFeedback = new ArrayList<>();
                req.setUrl("http://127.0.0.1/ProjetWebSymfony/test/web/app_dev.php/api/feedbacks/all");

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;                
                jsonp = new JSONParser();
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> mapFeedbacks = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapFeedbacks.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
                        Feedback feedback = new Feedback();
                                        Map<String, Object> livreur = (Map<String, Object>)obj.get("livreur");

                        float id = Float.parseFloat(obj.get("idFeed").toString());
                        float note = Float.parseFloat(obj.get("note").toString());
                        String contenu = obj.get("description").toString();
                        String date=obj.get("datefeedback").toString();
                        String f = obj.get("image").toString();         
                        float liv = Float.parseFloat(livreur.get("idEmp").toString());
                        

                        feedback.setLivreur((int)liv);
                                
                        System.out.println("COCOCOCOCOCOCOC I AM HERE = "+liv);
                        feedback.setId_feed((int)id);
                        feedback.setDatefeedback(date);
                        feedback.setDescription(contenu);
                       feedback.setNote((int)note);
                       feedback.setImage(f);
                        listFeedback.add(feedback);
                        System.out.println("data feedback => "+listFeedback.toString());

                    }
                } catch (IOException ex) {
                    System.out.println(ex);
                } 

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return listFeedback;
    }
    
    
    public String getNomLivreur(int id) 
     {                    Employe employe = new Employe();

     JSONArray s;
        ConnectionRequest con = new ConnectionRequest();
                con.setUrl("http://127.0.0.1/ProjetWebSymfony/test/web/app_dev.php/api/livreurs?idEmp="+id);

        con.addResponseListener((NetworkEvent e) -> {
            JSONParser jsonp = new JSONParser();
            try {
                Map<String, Object> obj = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                employe.setUSERNAME(obj.get("username").toString());
                

            } catch (IOException ex) {
                System.out.println("error related to sql :(");
            }
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return  employe.getUSERNAME();
    }
    

    public void ajouterNoteLivreur(Rating rating) {
        
                ConnectionRequest con = new ConnectionRequest();
                
                      String Url ="http://localhost/ProjetWebSymfony/test/web/app_dev.php/api/livreur/add/rate?ID_emp="+rating.getId()+
                              "&iduser="+SessionManager.getId()+"&note="+rating.getNote()+"&";

        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
          
          
    public void ajoutFeedback(Feedback rec) {
      
                ConnectionRequest con = new ConnectionRequest();
                
                      String Url = "http://localhost/ProjetWebSymfony/test/web/app_dev.php/api/feedbacks/add?description="+rec.getDescription()+"&image="+rec.getImage()+"&iduser="+rec.getIduser()+"&note="+rec.getNote()+"&idlivreur="+rec.getLivreur();
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    
    
    public int getNbrFeedback() {
        String url ="http://localhost/ProjetWebSymfony/test/web/app_dev.php/api/feedbacks/getNbr";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
   
            @Override
            public void actionPerformed(NetworkEvent evt) {
               nbrBeforeConvert  = new String(req.getResponseData());
              nbrAfterConvert   = Integer.parseInt(nbrBeforeConvert);
            }
            
        });
               NetworkManager.getInstance().addToQueueAndWait(req);
 
        return nbrAfterConvert;
        
    }
      
      
      
      

}

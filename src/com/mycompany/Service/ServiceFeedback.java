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

/**
 *
 * @author Chédy
 */
public class ServiceFeedback {
    
    
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

     
    
    
    
    public ArrayList<Employe> getFeedbacks() 
     {
        ArrayList<Employe> listReclamation = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
                con.setUrl("http://127.0.0.1/ProjetWebSymfony/test/web/app_dev.php/api/livreurs/all");

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;                
                jsonp = new JSONParser();
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapReclamations.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
                        Employe re = new Employe();
                        float id = Float.parseFloat(obj.get("idEmp").toString());
                    //    float idAuteur = Float.parseFloat(obj.get("id_auteur").toString());
                        String contenu = obj.get("username").toString();
                        String date=obj.get("email").toString();
                        String image = obj.get("mission").toString();
                        
                        
                            
                                    
                        re.setID_emp((int)id);
                        re.setUSERNAME(contenu);
                        re.setMISSION(image);
                       
                        listReclamation.add(re);
                        System.out.println("data livreur => "+listReclamation.toString());

                    }
                } catch (IOException ex) {
                    System.out.println(ex);
                } 

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listReclamation;
    }

    public void ajouterNoteLivreur(Rating rating) {
        
                ConnectionRequest con = new ConnectionRequest();
                
                      String Url ="http://localhost/ProjetWebSymfony/test/web/app_dev.php/api/livreur/add/rate?ID_emp="+rating.getId()+
                              "&iduser="+SessionManager.getId()+"&note="+rating.getNote();

        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
          
          
    public void ajoutFeedback(Feedback rec) {
      
                ConnectionRequest con = new ConnectionRequest();
                
                      String Url = "http://localhost/ProjetWebSymfony/test/web/app_dev.php/api/feedbacks/add?description="+rec.getDescription()+"&image="+rec.getImage()+"&iduser="+rec.getIduser()+"&note="+rec.getNote();
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
      
      
      
      

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Service;

import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.Entities.Reclamation;
import com.mycompany.gui.MesReclamations;
import com.mycompany.gui.SessionManager;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Chédy
 */
public class ServiceReclamation {
    
    public ArrayList<Reclamation> reclamations;
    
    public static ServiceReclamation instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceReclamation() {
         req = new ConnectionRequest();
    }

    public static ServiceReclamation getInstance() {
        if (instance == null) {
            instance = new ServiceReclamation();
        }
        return instance;
    }

       
    public void ajoutReclamation(Reclamation rec) {
      
                ConnectionRequest con = new ConnectionRequest();
                
                      String Url = "http://localhost/ProjetWebSymfony/test/web/app_dev.php/api/reclamations/addReclamation?objet="
                              +rec.getObjet()+"&description="+rec.getDescription()+"&image="+rec.getImage()+"&iduser="+rec.getIduser()+"&id_cat="+rec.getCat();

                //+ "&idUser="+SessionManager.getId();
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
      
      
      
      
      
      
    
      
    public ArrayList<Reclamation> getReclamations() 
     {
        ArrayList<Reclamation> listReclamation = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
                con.setUrl("http://127.0.0.1/ProjetWebSymfony/test/web/app_dev.php/api/reclamations/all");

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
                        Reclamation re = new Reclamation();
                        float id = Float.parseFloat(obj.get("idRec").toString());
                        String contenu = obj.get("objet").toString();
                        String date=obj.get("description").toString();
                        String image = obj.get("image").toString();
                        //float cat =Float.parseFloat( obj.get("idc").toString());
                        re.setId_rec((int) id);
                        String pathweb = obj.get("webPath").toString();
                        //
                                 String DateS = obj.get("date").toString().substring(obj.get("date").toString().indexOf("timestamp")+10,obj.get("date").toString().lastIndexOf("}"));
                   
                  Date currentTime = new Date(Double.valueOf(DateS).longValue()*1000);
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
String dateString = formatter.format(currentTime);
                        re.setDate(dateString);
                        
                        //
                        
                        
                        
                        re.setImage(image);
                        re.setObjet(contenu);
                        re.setDescription(date);
                        re.setWebPath(pathweb);
                     //   re.setCat((int)cat);
                        System.out.println("cat = "+re.getCat());
                        
                        listReclamation.add(re);
                        System.out.println("image => "+image);

                    }
                } catch (IOException ex) {
                    System.out.println(ex);
                } 

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listReclamation;
    }
     

  public Reclamation DetailRec(int id, Reclamation reclamation) {

        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://127.0.0.1/ProjetWebSymfony/test/web/app_dev.php/api/reclamations/find/" + id;
        con.setUrl(Url);
        con.addResponseListener((NetworkEvent e) -> {
            JSONParser jsonp = new JSONParser();
            try {
                Map<String, Object> obj = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                reclamation.setObjet(obj.get("objet").toString());
                reclamation.setDescription(obj.get("description").toString());
                reclamation.setEtat(Integer.parseInt(obj.get("etat").toString()));
                reclamation.setImage(obj.get("image").toString());

            } catch (IOException ex) {
                System.out.println("error related to sql :(");
            }
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return null;
    }
     public Image UrlImage(String photo){

        String url ="http://localhost/ProjetWebSymfony/test/web/app_dev.php/api/reclamations/images?image="+photo;
         System.out.println("url found in service == "+url);
                EncodedImage placeholder = EncodedImage.createFromImage(Resources.getGlobalResources().getImage("load.png"), false);
                Image urli = URLImage.createToStorage(placeholder,"Medium_"+url.toString(), url.toString(),URLImage.RESIZE_SCALE);
                
        return urli;        
    }
     
     
     
       public ArrayList<Reclamation> mesReclamations() {
        ArrayList<Reclamation> listReclamation = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/piweb-master/web/app_dev.php/api/mesTopicsMobile/"+SessionManager.getId());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> topics = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(topics);
                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) topics.get("root");
                    for (Map<String, Object> obj : list) {
                        Reclamation re = new Reclamation();
                                               float id = Float.parseFloat(obj.get("idRec").toString());
                        String contenu = obj.get("objet").toString();
                        String date=obj.get("description").toString();
                        String image = obj.get("image").toString();
                        //float cat =Float.parseFloat( obj.get("idc").toString());
                        re.setId_rec((int) id);
                        String pathweb = obj.get("webPath").toString();
                        //
                                 String DateS = obj.get("date").toString().substring(obj.get("date").toString().indexOf("timestamp")+10,obj.get("date").toString().lastIndexOf("}"));
                   
                  Date currentTime = new Date(Double.valueOf(DateS).longValue()*1000);
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
String dateString = formatter.format(currentTime);
                        re.setDate(dateString);
                        
                        //
                        
                        
                        
                        re.setImage(image);
                        re.setObjet(contenu);
                        re.setDescription(date);
                        re.setWebPath(pathweb);
                     //   re.setCat((int)cat);
                        System.out.println("cat = "+re.getCat());
                        
                        listReclamation.add(re);
                        System.out.println("image => "+image);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listReclamation;
    }
    
  




}

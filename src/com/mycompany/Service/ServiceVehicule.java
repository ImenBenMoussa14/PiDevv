/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Service;
import com.mycompany.Entities.Vehicule;
import com.mycompany.Entities.VehiculeUser;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import static com.codename1.ui.events.ActionEvent.Type.Calendar;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
        /**
 *
 * @author Maryem
 */
public class ServiceVehicule {
    
     public void ajoutOffre(VehiculeUser o) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/ProjetSymfony/test/web/app_dev.php/VehiculeBundle/new?"+"matricule="+o.getMatricule()+"&"+"idUser="+o.getId_user()+"&"+"dateDebut="+o.getDate_debut1()+"&"+"dateFin="+o.getDate_fin1()+"&"+"Etat="+o.getEtat();
        con.setUrl(Url);
           etatindispo( o);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
     public void etatindispo(VehiculeUser o) {
        ConnectionRequest con = new ConnectionRequest();
        String Url1 = "http://localhost/ProjetSymfony/test/web/app_dev.php/VehiculeBundle/upetatV/"+o.getMatricule();
       con.setUrl(Url1);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
     
    
      public boolean resultOK;
      
      
      public ArrayList<Vehicule> parseListOffreJson(String json) {

        ArrayList<Vehicule> listOffre = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> off = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) off.get("root");
            for (Map<String, Object> obj : list) {
                Vehicule v = new Vehicule();
                String matricule  = (obj.get("matricule").toString());
                String type  = (obj.get("type").toString());
                String puissance  = (obj.get("puissance").toString());
                String marque  = (obj.get("marque").toString());
                String kilometrages  = (obj.get("kilometrage").toString());
                String nbPlaces  = (obj.get("nbplace").toString());
                String prix  = (obj.get("prix").toString());
                String photo  = (obj.get("photo").toString());
                v.setMat(matricule);
                v.setType(type);
                v.setPuissance(puissance);
                v.setKilometrages(kilometrages);
                v.setNbPlaces(nbPlaces);
                v.setPrix(prix);
                v.setMarque(marque);
                v.setPhoto("http://localhost/mobile/"+photo);
                System.out.println(v);
                listOffre.add(v);
            }

        } catch (IOException ex) {
        }
        System.out.println(listOffre);
        return listOffre;

    }
    
    
    ArrayList<Vehicule> listVehicule = new ArrayList<>();
    
    public ArrayList<Vehicule> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/ProjetSymfony/test/web/app_dev.php/VehiculeBundle/all");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceVehicule ser = new ServiceVehicule();
                listVehicule = ser.parseListOffreJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listVehicule;
    }
    ////////////
          public ArrayList<VehiculeUser> parseListOffreJson1(String json) {

        ArrayList<VehiculeUser> listMesV = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> off = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) off.get("root");
            for (Map<String, Object> obj : list) {
                VehiculeUser v = new VehiculeUser();
                String matricule  = (obj.get("matricule").toString());
                String dateDebut  = (obj.get("dateDebut").toString());
                 String dateFin  = (obj.get("dateFin").toString());
                Date d =new Date((((Double)((Map<String, Object>)obj.get("dateDebut")).get("timestamp")).longValue()*1000));
                Date d1 =new Date((((Double)((Map<String, Object>)obj.get("dateFin")).get("timestamp")).longValue()*1000));
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                System.out.println(d);
                
               // String photo  = (obj.get("photo").toString());
                v.setMatricule(matricule);
                v.setDate_debut1(sdf.format(d));
                v.setDate_fin1(sdf.format(d1));
                System.out.println(v);
                listMesV.add(v);
            }

        } catch (IOException ex) {
        }
        System.out.println(listMesV);
        return listMesV;

    }
    
    
    
    ArrayList<VehiculeUser> MesVehicules = new ArrayList<>();
    
    public ArrayList<VehiculeUser> getListMesVehicules(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/ProjetSymfony/test/web/app_dev.php/VehiculeBundle/allC");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceVehicule ser = new ServiceVehicule();
                MesVehicules = ser.parseListOffreJson1(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return MesVehicules;
    }
    
    public void deleteVehicule(VehiculeUser o) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/ProjetSymfony/test/web/app_dev.php/VehiculeBundle/supp/"+o.getMatricule();
        con.setUrl(Url);
        etatdispo(o);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
       public void etatdispo(VehiculeUser o) {
        ConnectionRequest con = new ConnectionRequest();
        String Url1 = "http://localhost/ProjetSymfony/test/web/app_dev.php/VehiculeBundle/upetatVv/"+o.getMatricule();
       con.setUrl(Url1);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
}

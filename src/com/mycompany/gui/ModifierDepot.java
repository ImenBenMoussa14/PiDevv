/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.mycompany.Entities.Depot;


import com.mycompany.Service.DepotService;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;


public class ModifierDepot  extends BaseForm{
    
     public ModifierDepot(Resources res,Depot d) {
        super("Modifier un evenement", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Accueil");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
         TextField adresse = new TextField(d.getAdresse(), "Adresse", 20, TextField.ANY);
        TextField surface = new TextField(String.valueOf(d.getSurface()), "Surface", 20, TextField.ANY);
        TextField prix = new TextField(String.valueOf(d.getPrix()), "Prix", 20, TextField.ANY);
        TextField etat = new TextField(d.getEtat(), "Etat", 20, TextField.ANY);
        
        ComboBox etatCombo = new ComboBox();
        etatCombo.addItem("Disponible");
          etatCombo.addItem("Indisponible");
          if (d.getEtat().equals("dispo")){
               etatCombo.setSelectedIndex(0);
          }else{
               etatCombo.setSelectedIndex(1);
          }
         

        adresse.setUIID("TestField1");
        surface.setUIID("TestField1");
        prix.setUIID("TestField1");
        etat.setUIID("TestField1");

        adresse.setSingleLineTextArea(true);
        surface.setSingleLineTextArea(true);
        prix.setSingleLineTextArea(false);
        etat.setSingleLineTextArea(true);

        Button modifierbtn = new Button("Modifier");
        modifierbtn.setUIID("ButtonLouer");

            modifierbtn.addPointerPressedListener(ll->{
    
      d.setAdresse(adresse.getText());
      d.setSurface(Integer.valueOf(surface.getText()));
      d.setPrix(Integer.valueOf(prix.getText()));
      if (etatCombo.getSelectedIndex() == 0){
          d.setEtat("dispo");
      }else{
            d.setEtat("indispo");
      }
      
     if(DepotService.getInstance().modifierDepot(d)){
          new DepotAdminList(res);
      }
      });
        
       Button annulerbtn = new Button("Annuler");
   annulerbtn.addActionListener(l->{
       new DepotAdminList(res);
   });
        
        Label l2=new Label("");
         Label l3=new Label("");
          Label l4=new Label("");
           Label l5=new Label("");
        
        Label l1=new Label();
        Container content = BoxLayout.encloseY(
                l1,l2,
                new FloatingHint(adresse),
                createLineSeparator(),
                new FloatingHint(surface),
                createLineSeparator(),
                new FloatingHint(prix),
                createLineSeparator(),
                etatCombo,
                createLineSeparator(),
                l4,l5,
                modifierbtn,
                annulerbtn
        );
      
        
        add(content);
        show();
        
     }
}

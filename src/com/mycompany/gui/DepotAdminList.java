/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.mycompany.gui;

import com.mycompany.Entities.Depot;
import com.mycompany.Service.DepotService;


import com.codename1.components.FloatingHint;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import java.util.Date;

/**
 * The newsfeed form
 *
 * @author Shai Almog
 */
public class DepotAdminList extends BaseForm {

    public DepotAdminList(Resources res) {
        super("List des Depots", BoxLayout.y());
        
        removeAll();
        header(res);
          show();
        
       
        afficherDepot(res);
    }
    
    
    public void afficherDepot(Resources res){
          ButtonGroup barGroup = new ButtonGroup();
        RadioButton all = RadioButton.createToggle("All", barGroup);
        all.setUIID("SelectBar");
        RadioButton ajouter = RadioButton.createToggle("Ajouter", barGroup);
        ajouter.setUIID("SelectBar");
       
   
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(2, all, ajouter),
                FlowLayout.encloseBottom(arrow)
        ));
        
        all.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(all, arrow);
        });
        bindButtonSelection(all, arrow);
        bindButtonSelection(ajouter, arrow);
        
         ajouter.addActionListener( l ->{
         removeAll();
            header(res);
            show();
           afficherAjoutDepot(res);
          });
        
         all.addActionListener( l ->{
         removeAll();
            header(res);
            show();
            afficherDepot(res);
          });
        
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        
          ArrayList<Depot> depots;
        
         
         depots = DepotService.getInstance().getAllDepots();
         
          for (int i = 0; i < depots.size(); i++) {
              addButton(res, depots.get(i));
              show();
          }
    }
    
    public void afficherAjoutDepot(Resources res){
            ButtonGroup barGroup = new ButtonGroup();
        RadioButton all = RadioButton.createToggle("All", barGroup);
        all.setUIID("SelectBar");
        RadioButton ajouter = RadioButton.createToggle("Ajouter", barGroup);
        ajouter.setUIID("SelectBar");
       
   
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(2, all, ajouter),
                FlowLayout.encloseBottom(arrow)
        ));
        
        ajouter.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(ajouter, arrow);
        });
        bindButtonSelection(all, arrow);
        bindButtonSelection(ajouter, arrow);
        
         ajouter.addActionListener( l ->{
         removeAll();
            header(res);
            show();
           afficherAjoutDepot(res);
          });
        
         all.addActionListener( l ->{
         removeAll();
            header(res);
            show();
            afficherDepot(res);
          });
        
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        
        TextField adresse = new TextField("", "Adresse", 20, TextField.ANY);
        TextField surface = new TextField("", "Surface", 20, TextField.ANY);
        TextField prix = new TextField("", "Prix", 20, TextField.ANY);
        TextField etat = new TextField("", "Etat", 20, TextField.ANY);
        
        ComboBox etatCombo = new ComboBox();
        etatCombo.addItem("Disponible");
          etatCombo.addItem("Indisponible");

        adresse.setUIID("TestField1");
        surface.setUIID("TestField1");
        prix.setUIID("TestField1");
        etat.setUIID("TestField1");

        adresse.setSingleLineTextArea(true);
        surface.setSingleLineTextArea(true);
        prix.setSingleLineTextArea(false);
        etat.setSingleLineTextArea(true);

        Button ajouterbtn = new Button("ajouter");
        ajouterbtn.setUIID("ButtonLouer");

      ajouterbtn.addPointerPressedListener(ll->{
      Depot dnew = new Depot();
      dnew.setAdresse(adresse.getText());
      dnew.setSurface(Integer.valueOf(surface.getText()));
      dnew.setPrix(Integer.valueOf(prix.getText()));
      if (etatCombo.getSelectedIndex() == 0){
          dnew.setEtat("dispo");
      }else{
            dnew.setEtat("indispo");
      }
      
     if(DepotService.getInstance().addDepot(dnew)){
          new DepotAdminList(res);
      }
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
                ajouterbtn
        );
      
        
        add(content);
        show();
    }
    
    public void header(Resources res){
         Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("List des Depots");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
   
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("depot1.jpg"), spacer1);
        addTab(swipe, res.getImage("depot2.jpg"), spacer2);
                
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
        
        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        
      
    }
    
    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
        
        
    }
    
    private void addTab(Tabs swipe, Image img, Label spacer) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
       
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
        
        Container page1 = 
            LayeredLayout.encloseIn(
                image,
                overlay,
                BorderLayout.south(
                    BoxLayout.encloseY(
                           
                            spacer
                        )
                )
            );

        swipe.addTab("", page1);
    }
    
   private void addButton(Resources res ,Depot d) {
       
      Image img = res.getImage("depot.jpg") ;
       int height = Display.getInstance().convertToPixels(13.5f);
       int width = Display.getInstance().convertToPixels(18f);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
       
       Container cnt = BorderLayout.west(image);
      // cnt.setLeadComponent(image);
      
       Label prixtxt = new Label("Prix :","NewsTopLine2");
       Label surfacetxt = new Label("Surface :","NewsTopLine2");
       
       Label prix = new Label(String.valueOf(d.getPrix())+" DT","NewsTopLine");
       prix.setTextPosition(RIGHT);
     
       
       Label surface = new Label(String.valueOf(d.getSurface())+ " metre","NewsTopLine");
       surface.setTextPosition(RIGHT);
      
       
       Label adresse = new Label(d.getAdresse(),"NewsTopLine");
       adresse.setTextPosition(RIGHT);
       Style adresseImageSt = new Style(adresse.getUnselectedStyle());
       adresseImageSt.setFgColor(0x328eb3);
       FontImage adresseImage = FontImage.createMaterial(FontImage.MATERIAL_ROOM, adresseImageSt);
       adresse.setIcon(adresseImage);
       
       Label lsupprimer = new Label("   ");
        lsupprimer.setUIID("NewsTopLine");
        Style supprimertStyle = new Style(lsupprimer.getUnselectedStyle());
        supprimertStyle.setFgColor(0xf21f1f);
        FontImage supprimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprimertStyle);
        lsupprimer.setIcon(supprimerImage);
        lsupprimer.setTextPosition(LEFT);

        lsupprimer.addPointerPressedListener(l -> {
           Dialog dig = new Dialog("Suppression");
           
          if ( dig.show("Suppression", "vous voulez supprimer ce depot ?", "Annuler", "Oui")){
              dig.dispose();
            
          }else{
                 dig.dispose();
                 if(DepotService.getInstance().deleteDepot(d.getId())){
                 new DepotAdminList(res);
            }
          }
          
        
        });

        Label lmodifier = new Label("   ");
        lmodifier.setUIID("NewsTopLine");
        Style modifierStyle = new Style(lmodifier.getUnselectedStyle());
        modifierStyle.setFgColor(0xf7ad02);
        FontImage modifierImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
        lmodifier.setIcon(modifierImage);
        lmodifier.setTextPosition(LEFT);
        
           lmodifier.addPointerPressedListener(l -> {
            new ModifierDepot(res, d);
        });
       
       Label etat ;
     //   prix.setTextPosition();
       
       if (d.getEtat().equals("dispo")){
                      
         etat = new Label("Disponible","EtatDisponibleTextStyle");
        etat.addPointerPressedListener(e -> ToastBar.showMessage("Depot disponible", FontImage.MATERIAL_INFO));
       
        }else{
         etat = new Label("Indisponible","EtatInDisponibleTextStyle");
        
            etat.addPointerPressedListener(e -> ToastBar.showMessage("Depot indisponible", FontImage.MATERIAL_INFO));
       }
       
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       adresse,
                       BoxLayout.encloseX(prixtxt, prix),
                        BoxLayout.encloseX(surfacetxt , surface),
                        BoxLayout.encloseX(etat,lmodifier,lsupprimer)
              ));
        
        
       add(cnt);
       
        add(createLineSeparator(0x5e99bd));
        add(createLineSeparator(0x5e99bd));
        add(createLineSeparator(0x5e99bd));
        
       image.addActionListener(e -> ToastBar.showMessage(d.getAdresse(), FontImage.MATERIAL_INFO));
   }
    
    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
}

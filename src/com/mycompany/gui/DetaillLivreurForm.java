/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InteractionDialog;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.Entities.Employe;
import com.mycompany.Entities.Rating;
import com.mycompany.Service.ServiceFeedback;

import java.io.IOException;


/**
 *
 * @author Mechlaoui
 */
public class DetaillLivreurForm extends BaseForm {

  //  Colocation colocation = new Colocation();

    public DetaillLivreurForm(Resources res, Employe  ev) {
      
        super(BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setTitle("Esprit Entr'Aide");
        getContentPane().setScrollVisible(true);
        super.addSideMenu(res);
        Form previous = Display.getInstance().getCurrent();
        tb.addCommandToRightBar(null, res.getImage("backmenu.png"), e -> previous.showBack());
        //tb.setBackCommand("", e -> previous.showBack());

        Tabs swipe = new Tabs();
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        addTab(swipe, res.getImage("photo-profile.jpg"), ev.getADRESSE(), ev);
        add(LayeredLayout.encloseIn(swipe));

    }
    

   

    private void addTab(Tabs swipe, Image img, String text, Employe ev) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if (img.getHeight() < size) {
            img = img.scaledHeight(size);
        }

        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FIT);
        Label overlay = new Label(" ", "ImageOverlay");
        Slider star = createStarRankSlider();
        //Button reserver = new Button();
        int height = Display.getInstance().convertToPixels(13.5f);
        int width = Display.getInstance().convertToPixels(14f);
        Button reserver = new Button(Resources.getGlobalResources().getImage("reserver.png").fill(width, height));
        //reserver.setIcon(l.getIcon());
        reserver.setUIID("Label");
        reserver.addActionListener((booking) -> {
            SpanLabel sp = new SpanLabel();
            InteractionDialog dlg = new InteractionDialog("");
            dlg.setLayout(new BorderLayout());
            sp.setText("Username: " + ev.getUSERNAME()+ "\n"
                    + "Adresse: " + ev.getADRESSE()+ "\n"
                            + "Governrat: " + ev.getGovernat()+ "\n"
                                    + "Email: " + ev.getEMAIL()+ "\n"
                                            + "Places disponibles: " + ev.getTelephone()+ "\n"
            );
            sp.setTextUIID("textDialog");
            dlg.add(BorderLayout.CENTER, sp);
            Button sms = new Button("Choisir");
            sms.addActionListener((bookin) -> {
                // TwilioSms sms1 = new TwilioSms();
                
                /*ServiceCol col = new ServiceCol();
                col.deleteColocation(colocation.getId());*/
                SessionManager sm = new SessionManager();
                //sms1.sendSms("l'etudiant "+sm.getUserName()+" a choisi l'annonce dont l'addresse est " + ev.getAdresse());
                // Dialog.show("Un message est envoyée au propriétaire", null, "ok", null);
            });
            Button fav=new Button("Favoris");
            fav.addActionListener((favv)->{
                //   db.execute("insert into Favoris (type_colocation, adresse,prix,sexe,place_dispo,type_maison,description,path) values ('" + ev.getType()+ "', '" + ev.getAdresse() + "', '" + ev.getPrix() + "', '" + ev.getSexe() + "', '" + ev.getPlace_dispo() + "', '" + ev.getType_maison() + "', '" + ev.getDescription() + "', '" + ev.getPath()+ "');");
                // System.out.println("element inser");
                //Cursor cur = db.executeQuery("select * from Favoris");
                //  AfficherFavCol afc = new AfficherFavCol(Resources.getGlobalResources(), cur);
                // afc.show();
                
            });
            Button close = new Button("Fermer");
            close.addActionListener((ee) -> dlg.dispose());
            Container cnt = new Container(BoxLayout.x());
            cnt.add(fav);
            cnt.add(sms);
            cnt.add(close);
            dlg.addComponent(BorderLayout.SOUTH, cnt);
            Dimension pre = dlg.getContentPane().getPreferredSize();
            dlg.show(0, 160, 50, 0);
            //dlg.show(0, 0, Display.getInstance().getDisplayWidth() - (pre.getWidth() + pre.getWidth() / 6), 0);
        });

        Container page1
                = LayeredLayout.encloseIn(
                        image,
                        overlay,
                        BorderLayout.south(
                                BoxLayout.encloseY(
                                        star,
                                        BoxLayout.encloseX(reserver, new SpanLabel(text, "LargeWhiteText"))
                                )
                        )
                );
        star.addActionListener((e) -> {
            int rate = star.getProgress();
           // Rating rating = new Rating(SessionManager.getId(),rate, )
            System.out.println(rate);
            //ServiceFeedback.getInstance().ajouterNoteLivreur(rating);
            
            
            Database db;
                
             //   db = Database.openOrCreate("rating.db");
               // db.execute("create table if not exists Rating (iduser INTEGER, value INTEGERon TEXT,path VARCHAR(255));\"");
                

        });

        swipe.addTab("", page1);
    }

    private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }

    private Slider createStarRankSlider() {
        Slider starRank = new Slider();
        starRank.setEditable(true);
        starRank.setMinValue(0);
        starRank.setMaxValue(5);
        Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
                derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
        Style s = new Style(0xffff33, 0, fnt, (byte) 0);
        Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        s.setOpacity(100);
        s.setFgColor(0);
        Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
        initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
        starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
        return starRank;

    }

}
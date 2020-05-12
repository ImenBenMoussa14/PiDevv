/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import static com.codename1.io.Log.e;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Slider;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.Entities.Employe;
import com.mycompany.Entities.Feedback;
import com.mycompany.Entities.Reclamation;
import com.mycompany.Service.ServiceFeedback;
import com.mycompany.Service.ServiceReclamation;
import java.io.IOException;

import java.util.ArrayList;

/**
 * The newsfeed form
 *
 * @author Shai Almog
 */
public class ListLivreurForm extends BaseForm {
    public ListLivreurForm(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Livreurs");
        getContentPane().setScrollVisible(false);


      
        tb.addSearchCommand(e -> {
        });

        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, spacer1, res.getImage("back-logo.png"), "", "",res);

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
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton mesListes = RadioButton.createToggle("Livreurs", barGroup);
        mesListes.setUIID("SelectBar");
       RadioButton liste = RadioButton.createToggle("Mes Feedback", barGroup);
       liste.setUIID("SelectBar");
        RadioButton partage = RadioButton.createToggle("Feedback Livreur", barGroup);
        partage.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");

       

      //   liste.addActionListener((e) -> {
        //     System.out.println("hii");
          //  MesReclamations a = new MesReclamations(res);
           // a.show();
        //});
        
       

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3,liste,mesListes, partage),
                FlowLayout.encloseBottom(arrow)
        ));

        mesListes.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
       updateArrowPosition(liste, arrow);
        });
       bindButtonSelection(liste, arrow);
        bindButtonSelection(partage, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });

        ServiceFeedback serviceFeedback = new ServiceFeedback();
        ArrayList<Feedback> lis = serviceFeedback.getFeedbacks();
        for (Feedback rec : lis) {
       //     String url="http://127.0.0.1/ProjetWebSymfony/test/web/uploads/reclamation_image/"+rec.();
            
            Image placeholder = Image.createImage(120, 90);
            
            EncodedImage enc = EncodedImage.createFromImage(placeholder, false);
               AjouterFeedbackForm aj = new AjouterFeedbackForm(res,rec);
             Slider star = aj.createStarRankSlider();
              partage.addActionListener((e) -> {
            AjouterFeedbackForm a = new AjouterFeedbackForm(res,rec);
            a.show();
            
            
        });
             
                     partage.setUIID("SelectBar");
            URLImage urlim = URLImage.createToStorage(enc, "news-item.jpg","news-item.jpg", URLImage.RESIZE_SCALE);
        Image img = res.getImage("user.png");
        Button btn = new Button("Notez");
        
        //Get Livreur
        if(rec.getNote() == 0) {
           btn.setVisible(true);
           star.setVisible(false);
           
        }
        else {
              btn.setVisible(false);
           star.setVisible(true);
                      star.setProgress(rec.getNote());

        }

        int id  = rec.getLivreur();
       String nomLivreur = serviceFeedback.getNomLivreur(id);
            System.out.println("here im liv = "+nomLivreur);
        //


        //          SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//            String dateS = format.format(rec.getDate());
            addButton(img,nomLivreur, rec, btn, star);
           
        }
    }

    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();

    }


    private void addTab(Tabs swipe, Label spacer,Image img, String commentsStr, String text,Resources res) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
       if (img.getHeight() < size) {
           img = img.scaledHeight(size);
        }
        Button likes = new Button();
        Style heartStyle = new Style(likes.getUnselectedStyle());
      //  heartStyle.setFgColor(0xff2d55);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, heartStyle);
        likes.setIcon(heartImage);
        likes.setTextPosition(RIGHT);
        
        likes.addActionListener(e -> {
            new NewsfeedForm(res).showBack();
        });
        


        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");

          

               
     //   star.setUIID("TextFieldBlack");
        Container page1
                = LayeredLayout.encloseIn(
                       image,
                        overlay,
                        BorderLayout.south(
                                BoxLayout.encloseY(
                                        new SpanLabel(text, "LargeWhiteText"),
                                        FlowLayout.encloseIn(likes),
                                        spacer
                                )
                        )
                );

        swipe.addTab("",res.getImage("back-logo.png"), page1);
    }


    private void addButton(Image img, String title ,Feedback evv,Button s,Slider star) {
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
        cnt.setLeadComponent(image);
        TextArea ta = new TextArea(title);
        ta.setUIID("NewsTopLine");
        ta.setEditable(false);

     //Label details = new Label("Notez moi","CenterLabel");
    //   details.setUIID("Details");       
      
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       ta,
                       s,star
               ));
       
       //image.addActionListener((ActionEvent e) -> {
         // new AjouterFeedbackForm(Resources.getGlobalResources()).show();
          
           //    });
        add(cnt);
        image.addActionListener(e -> new AjouterFeedbackForm(Resources.getGlobalResources(), evv).show());
    }

    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if (b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
}
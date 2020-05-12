package com.mycompany.gui;

import com.codename1.capture.Capture;
import com.codename1.components.ScaleImageLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;

import com.mycompany.Entities.RecFeedCat;
import com.mycompany.Service.ServiceRecFeedCat;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

/**
 * @author Chedy
 */
public class ModifierCategorieRecFeedForm extends BaseForm {

    private static String i;

    public ModifierCategorieRecFeedForm(Resources res,RecFeedCat edit_cat) {
        super(BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setTitle("Modifier Categorie");
        getContentPane().setScrollVisible(true);
        super.addSideMenu(res);

        Image img = res.getImage("back-logo.png");
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
    //    Label pp = new Label(Ser.UrlImage(SessionManager.getPhoto()), "PictureWhiteBackgrond");


        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                        GridLayout.encloseIn(3,
                                FlowLayout.encloseCenter(
                                        //pp)
                                )
                        )
                )
        ));

   
        
        
        add(LayeredLayout.encloseIn(
               // GridLayout.encloseIn(1, elements)
        ));
        
        TextField nom = new TextField("");
        nom.setText(edit_cat.getNom());
        nom.setUIID("LabelIcon");
        addStringValue("Nom categorie", nom);
        nom.addActionListener((eeee)->{
            System.out.println(nom.getText());
        });
        
               
      
        
        Button picture = new Button("Choisir image");
        picture.setUIID("Update");
        addStringValue("", picture);
                
        Button ajout = new Button("Modifier");
        ajout.setUIID("Edit");
        addStringValue("", ajout); 
        
        
            
        
        TextField path = new TextField("");
        
                picture.addActionListener(e -> {
            i = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
            if (i != null) {
                try{
                Image im;
                im = Image.createImage(i);
                 im = im.scaled(res.getImage("photo-profile.jpg").getWidth(),
                      res.getImage("photo-profile.jpg").getHeight());
              //       pp.setIcon(im);
                System.out.println(i);
                path.setText(i);
                
                //System.out.println("The new image's name is : "+Session.getTmpImage());
            }catch(Exception execption ) {
                execption.printStackTrace();
            }
            }
        });
                
        ajout.addActionListener((edit)-> {
          
              
            
            RecFeedCat recFeed = new RecFeedCat(edit_cat.getId_cat(),edit_cat.getNom(),edit_cat.getImage());
            ServiceRecFeedCat.getInstance().editRecFeedCat(recFeed,path.getText());
            new AcceuillForm(res).show();                    
            refreshTheme();    
            
            
        });
        
    } 

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
    
    private Container addStringValuePrix(String s, Component v) {
        Container cnt = BorderLayout.west(new Label(s, "PaddedLabel"));
                cnt.add(BorderLayout.CENTER, v);
                cnt.add(BorderLayout.SOUTH,createLineSeparator(0xeeeeee));
        return cnt;        
    }
    
}
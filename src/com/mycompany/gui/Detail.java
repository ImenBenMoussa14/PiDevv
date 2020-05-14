/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ImageViewer;
import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.Entities.Produit;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.TreeMap;

/**
 *
 * @author amall
 */
public class Detail extends BaseForm{
    
    Produit p;
    Form form;
    Resources res;
    public Detail(Resources res, Produit p) {
    
        this.p = p;
        this.res = res;
        if (form == null) {
            form = new Form(new FlowLayout(Component.CENTER));

        }
        ImageViewer img = new ImageViewer();
        Image placeholder = Image.createImage(1500, 1200);
        EncodedImage enc = EncodedImage.createFromImage(placeholder, false);
        URLImage uRLImage = URLImage.createToStorage(enc,
                p.getPhoto(), Statics.IMAGE_URL+"/"+p.getPhoto());        
        img.setImage(uRLImage);
                Container c = new Container(BoxLayout.y());

      /*  Button valider = new Button("Ajouter au panier");
        Slider s = new Slider();
        s.setEditable(true);
        s.setMaxValue(p.getQuantite());
        s.setMinValue(0);
        s.addDataChangedListener(new DataChangedListener() {
            @Override
            public void dataChanged(int type, int index) {
                valider.setText("Valider " + s.getProgress());
            }
        });
        valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                        Dialog.show("Info", "Produit ajouté avec succés", new Command("ok"));
                    }
        });*/

        c.add(img);
        Container c2 = new Container(BoxLayout.y());
        c2.add(new Label("Libellé: "+p.getNom_pd()));
        c2.add(new Label("Prix: "+p.getPrix()+"DT"));
        c2.add(new Label("Description: "+p.getDescription()));
        c2.add(new Label("Stock: "+p.getQuantite()+" articles"));


        c.add(c2);
        /*c.add(new Label("Quantité à commander:"));
        s.getAllStyles().setMargin(5, 5, 5, 5);
        s.getAllStyles().setFgColor(ColorUtil.MAGENTA);
        c.add(s);
        c.add(valider);*/

        form.add(c);
/* form.getToolbar().addMaterialCommandToOverflowMenu("Voir Panier",
            FontImage.MATERIAL_SHOPPING_CART,
            new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                               //new Vitrine(res).show();

            }
        });*/
    }

    public Form getForm() {
        return form;
    }
}

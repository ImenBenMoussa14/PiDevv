/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.mycompany.Entities.Vehicule;
import com.mycompany.Entities.VehiculeUser;
import com.mycompany.Service.ServiceVehicule;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import static com.codename1.ui.Component.TOP;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
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
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.io.IOException;

/**
 *
 * @author Maryem
 */
public class MesVehicules extends BaseForm {

    Image imgs, imgss;
    ImageViewer imgv;
    EncodedImage enc;
    private Resources theme;
    ServiceVehicule sv = new ServiceVehicule();
    Form f = new Form(new BoxLayout(BoxLayout.Y_AXIS));

    public MesVehicules(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Binvenue");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("back", e -> previous.showBack());
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        tb.addSearchCommand(e -> {
        });

        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("profile-background.jpg"), spacer1, "  ", "", "Mes Vehicules");
        //  addTab(swipe, res.getImage("dog.jpg"), spacer2, "100 Likes  ", "66 Comments", "Dogs are cute: story at 11");

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
        RadioButton all = RadioButton.createToggle("Mes vehicules ", barGroup);
        all.setUIID("SelectBar");

        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(1, all),
                FlowLayout.encloseBottom(arrow)
        ));

        all.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(all, arrow);
        });
        bindButtonSelection(all, arrow);

        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        //  Vehicule o = new Vehicule();
        ServiceVehicule sv = new ServiceVehicule();
        for (VehiculeUser o : sv.getListMesVehicules()) {
            try {
                enc = EncodedImage.create("/1.png");
            } catch (IOException ex) {
            }
            addButton(o, enc, "Matricule :" + o.getMatricule() + ", Date debut location :"
                    + o.getDate_debut1() + " Date Fin location :" + o.getDate_fin1(),
                    false, 0, 0);
            /*  addButton(res.getImage("news-item-2.jpg"), "Fusce ornare cursus masspretium tortor integer placera.", true, 15, 21);
        addButton(res.getImage("news-item-3.jpg"), "Maecenas eu risus blanscelerisque massa non amcorpe.", false, 36, 15);
        addButton(res.getImage("news-item-4.jpg"), "Pellentesque non lorem diam. Proin at ex sollicia.", false, 11, 9);*/
        }
    }

    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();

    }

    private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if (img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
        Label likes = new Label(likesStr);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
        likes.setIcon(heartImage);
        likes.setTextPosition(RIGHT);

        Label comments = new Label(commentsStr);
        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");

        Container page1
                = LayeredLayout.encloseIn(
                        image,
                        overlay,
                        BorderLayout.south(
                                BoxLayout.encloseY(
                                        new SpanLabel(text, "LargeWhiteText"),
                                        FlowLayout.encloseIn(likes, comments),
                                        spacer
                                )
                        )
                );

        swipe.addTab("", page1);
    }

    private void addButton(VehiculeUser v, Image img, String title, boolean liked, int likeCount, int commentCount) {
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
        cnt.setLeadComponent(image);
        TextArea ta = new TextArea(title);
        ta.setUIID("NewsTopLine");
        ta.setEditable(false);

        Label likes = new Label(likeCount + " Likes  ", "NewsBottomLine");
        likes.setTextPosition(RIGHT);
        if (!liked) {
            FontImage.setMaterialIcon(likes, FontImage.MATERIAL_FAVORITE);
        } else {
            Style s = new Style(likes.getUnselectedStyle());
            s.setFgColor(0xff2d55);
            FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);
            likes.setIcon(heartImage);
        }
        Label comments = new Label(commentCount + " Comments", "NewsBottomLine");
        FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);

        cnt.add(BorderLayout.CENTER,
                BoxLayout.encloseY(
                        ta,
                        BoxLayout.encloseX(likes, comments)
                ));
        add(cnt);
        //   image.addActionListener(e -> ToastBar.showMessage(title, FontImage.MATERIAL_INFO));
        image.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ModifVehicules(v).show();
            }

        });
    }

    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if (b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }

    public Form ModifVehicules(VehiculeUser v) {
         Form previous = Display.getInstance().getCurrent();
        Form f2 = new Form(new BoxLayout(BoxLayout.Y_AXIS));
        Picker dp1 = new Picker();
        Picker dp2 = new Picker();
        Container ct = new Container(BoxLayout.x());
        Container ctd1 = new Container(BoxLayout.x());
        Container ctd2 = new Container(BoxLayout.x());
        Label l1 = new Label("Matricule");
        Label l2 = new Label("Loueé le:");
        Label l3 = new Label("Le contrat se termine le:");
        Label l4 = new Label(v.getMatricule());
        Label l5 = new Label(v.getDate_debut1());
        Label l6 = new Label(v.getDate_fin1());
        
         Font ttfFontt = Font.createSystemFont(Font.CENTER, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
        Font ttfFont = Font.createSystemFont(Font.FACE_PROPORTIONAL, Font.STYLE_ITALIC, Font.SIZE_MEDIUM);
        l1.getUnselectedStyle().setFont(ttfFontt);
        l2.getUnselectedStyle().setFont(ttfFontt);
        l3.getUnselectedStyle().setFont(ttfFontt);
        l4.getUnselectedStyle().setFont(ttfFont);
         l5.getUnselectedStyle().setFont(ttfFont);
         l6.getUnselectedStyle().setFont(ttfFont);
        dp1.getUnselectedStyle().setFgColor(CENTER);
        dp2.getUnselectedStyle().setFgColor(CENTER);
        l1.getUnselectedStyle().setFgColor(CENTER);
        l2.getUnselectedStyle().setFgColor(CENTER);
        l3.getUnselectedStyle().setFgColor(CENTER);
          l4.getUnselectedStyle().setFgColor(CENTER);
           l5.getUnselectedStyle().setFgColor(CENTER);
            l6.getUnselectedStyle().setFgColor(CENTER);
        ct.add(l1);
        ct.add(l4);
        f2.add(ct);

         ctd1.add(l2);
        ctd1.add(l5);
        f2.add(ctd1);
        
       // f2.add(l2);
        ctd2.add(l3);
        ctd2.add(l6);
        f2.add(ctd2);
   //   f2.add(l5);
     //   f2.add(l3);
//        f2.add(l6);
        dp1.setDate(v.getDate_debut());
        System.out.println(dp1.getDate());
        dp1.setDate(v.getDate_fin());
        f2.getStyle().setBgTransparency(255);
        f2.getStyle().setBgColor(14204888);
        Button bt1 = new Button("Modifier");
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

            }
        });
        Button bt2 = new Button("Supprimer");
        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ServiceVehicule sv = new ServiceVehicule();
                sv.deleteVehicule(v);
                Dialog.show("Succès", "Vous avez bien supprimer ce contrat", "Ok", null);
               
                previous.show();
            }
        });
        Button bt3 = new Button("Retour");
        bt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                previous.show();
            }
        });
        f2.add(bt1);
        f2.add(bt2);
        f2.add(bt3);
        return f2;
    }
}


package com.mycompany.gui;

import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.Entities.Produit;
import com.mycompany.Service.ServiceProduit;
import com.mycompany.utils.Statics;
import com.mycompany.gui.Detail;
import com.mycompany.gui.Panier;
import java.io.IOException;
import java.util.ArrayList;
import com.codename1.components.FloatingHint;
import com.codename1.components.ScaleImageButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import static com.codename1.io.Log.p;
import com.codename1.ui.AutoCompleteTextField;
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
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.NumericSpinner;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.Entities.Commande;
import com.mycompany.Entities.LignePanier;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 *
 * @author amal
 */
public class Vitrine extends BaseForm{
            static Set<LignePanier> spanier = new HashSet<LignePanier>();
            static public String entry="";
            
public Vitrine(Set < LignePanier > sp, Resources res) {
        super("Vitrine", BoxLayout.y());
        removeAll();
        header(res);
        show();
        afficherProduits(res);
    }
   
public void afficherProduits(Resources res){
        //this is the menu
        ButtonGroup barGroup = new ButtonGroup();
        RadioButton pdt = RadioButton.createToggle("Nos produits", barGroup);
        pdt.setUIID("SelectBar");
        RadioButton pn = RadioButton.createToggle("Panier", barGroup);
        pn.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        TextField searchField = new TextField("", "🔍| Chercher..."); 
        searchField.setUIID("Title");
        ComboBox categories = new ComboBox();
        categories.addItem("Tout");
        categories.addItem("Informatique");
        categories.addItem("Immobilier");
        categories.addItem("Vetetements");
        categories.addItem("Autre");
              
        ComboBox tri = new ComboBox();
        tri.addItem("A-Z");
        tri.addItem("Z-A");
        tri.addItem("Moins cher");
        tri.addItem("Plus cher");

        Button ok = new Button("ok!");


        
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(2, pdt, pn),
                FlowLayout.encloseBottom(arrow)
        ));
        add(GridLayout.encloseIn(2,searchField,ok));
       add(GridLayout.encloseIn(2,categories,tri));
        
        pdt.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(pdt, arrow);
        });
        bindButtonSelection(pdt, arrow);
        bindButtonSelection(pn, arrow);
        //action de clique sur menu panier
  pn.addActionListener(e -> new Panier(spanier,res).show());


        //action de clique sur menu produit
         pdt.addActionListener( l ->{
         removeAll();
            header(res);
            show();
            afficherProduits(res);
          });
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        //needs fixing!!!!!
        ServiceProduit sp = new ServiceProduit();
        if(entry.equals("")){
        ArrayList<Produit> produits = sp.getAllProduits();
          for (int i = 0; i < sp.getAllProduits().size(); i++) {
   
             final Produit p = produits.get(i);
       
                 String url = (Statics.IMAGE_URL+"/"+p.getPhoto());
                 Image placeholder = Image.createImage(120, 90);
                 EncodedImage enc = EncodedImage.createFromImage(placeholder, false);
                 URLImage urlim = URLImage.createToStorage(enc, url,url, URLImage.RESIZE_SCALE);
                 addButton(urlim, produits.get(i), p, res);
                 show();
            
          }
          

          }
  
  else{
          System.out.println("testing else"+entry); 
          ArrayList<Produit> produits2 = sp.getSearchedProduits();
                    System.out.println(produits2); 

          for (int i = 0; i < sp.getSearchedProduits().size(); i++) {
   
             final Produit p2 = produits2.get(i);
       
                 String url = (Statics.IMAGE_URL+"/"+p2.getPhoto());
                 Image placeholder = Image.createImage(120, 90);
                 EncodedImage enc = EncodedImage.createFromImage(placeholder, false);
                 URLImage urlim = URLImage.createToStorage(enc, url,url, URLImage.RESIZE_SCALE);
                 addButton(urlim, produits2.get(i), p2, res);
                 show();
            
          }     
                  }
        //needs fixing!!! l'appel d'afficher produits recrée le menu et laisse le resultat initial de entry =""
         ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
            entry=searchField.getText().trim();
            System.out.println(entry);
            String categorie=(String) categories.getSelectedItem();
            System.out.println(categorie);
            String ordre=(String) tri.getSelectedItem();
            System.out.println(ordre);
            afficherProduits(res);
            }
        }); 
          
    }

       
        
      private void addButton(Image img  ,Produit i , Produit pp, Resources res) {
       int height = Display.getInstance().convertToPixels(13.5f);
       int width = Display.getInstance().convertToPixels(18f);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
       
       Container cnt = BorderLayout.west(image);
       //cnt.setLeadComponent(image);
       Label nomtxt = new Label("Libellé :","NewsTopLine2");
       Label prixtxt = new Label("Prix :","NewsTopLine2");
       
       Label nom = new Label(String.valueOf(pp.getNom_pd()),"NewsTopLine");
       nom.setTextPosition(RIGHT);
       
       Label prix = new Label(String.valueOf(pp.getPrix())+" DT","NewsTopLine");
       prix.setTextPosition(RIGHT);
     
       
               
       Label ajt = new Label(" ","panierTextStyle");
       ajt.setTextPosition(RIGHT);       
       Style ajtImageSt = new Style(ajt.getUnselectedStyle());
       ajtImageSt.setFgColor(0x328eb3);
       
       FontImage PanierImage = FontImage.createMaterial(FontImage.MATERIAL_ADD_SHOPPING_CART, ajtImageSt);
       ajt.setIcon(PanierImage);
       
 
       ajt.addPointerPressedListener(e -> {
           
                            LignePanier lpn = new LignePanier(pp.getId_pro(),pp.getNom_pd(),pp.getPrix(),pp.getPhoto(),pp.getQuantite());
                            spanier.add(lpn);
                            System.out.println("Panier = " + spanier.toString());
                        } 
                    );
             cnt.add(BorderLayout.CENTER,
               BoxLayout.encloseY(
                       BoxLayout.encloseX(nomtxt, nom),
                       BoxLayout.encloseX(prixtxt , prix),
                       BoxLayout.encloseX(ajt)
               ));
        add(cnt);
        add(createLineSeparator(0x5e99bd));
        add(createLineSeparator(0x5e99bd));
        add(createLineSeparator(0x5e99bd));

        image.addActionListener(e -> new Detail(res, pp).getForm().show());
      }
      
  
                  
      
          
        public void pass(Set<LignePanier> sp){
        spanier.addAll(sp);
    }  
    public void header(Resources res){
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Vitrine");
        getContentPane().setScrollVisible(false);
        super.addSideMenu(res);
        Tabs swipe = new Tabs();
        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("cover.jpg"), spacer1);
        addTab(swipe, res.getImage("dog.jpg"), spacer2);
               
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
    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
        
    }
     
}     


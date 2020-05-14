
package com.mycompany.gui;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.SmsSubmissionResponseMessage;
import com.nexmo.client.sms.messages.TextMessage;
import com.mycompany.gui.SessionManager;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.TextArea;
import com.codename1.ui.Form;
import com.codename1.components.ToastBar;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Slider;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.Entities.Commande;
import com.mycompany.Entities.LignePanier;
import com.mycompany.Entities.Produit;
import com.mycompany.utils.Statics;
import java.util.Map;
import java.util.TreeMap;
import com.mycompany.gui.Detail;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.mycompany.gui.SessionManager;
/**
 *
 * @author amal
 */
public class Panier extends BaseForm{

             Set < LignePanier > spanier = new HashSet < LignePanier > ();
             SessionManager sm = new SessionManager();

public Panier(Set < LignePanier > sp, Resources res) {
       super("Panier", BoxLayout.y());
       spanier= Vitrine.spanier;
       System.out.println("tester const de panier"+spanier);
       removeAll();
       show();
       render(sp, res);
        
        }
//needs fixing !!!!!!!!
   Label prix_total= new Label();
 public void computeTotal(List < LignePanier > lst) {
  if (lst.isEmpty()) {
   prix_total.setText(String.valueOf(0));
  }

 }
   private void afficherVitrine (Resources res){
                         
            new Vitrine(spanier,res);
            System.out.println("tester afficherVitrine"+spanier);

        }
   
public void render(Set < LignePanier > sp, Resources res) {
  spanier.addAll(sp);
  System.out.println("test in panier spanier = " + spanier.toString());
  List < LignePanier > lst = new ArrayList < LignePanier > (spanier);
  System.out.println("lst = " + lst.toString());


  for (int i = 0; i < lst.size(); i++) {
             final LignePanier lp = lst.get(i);
                      String url = (Statics.IMAGE_URL+"/"+lp.getPhoto());
                      Image placeholder = Image.createImage(120, 90);
                        EncodedImage enc = EncodedImage.createFromImage(placeholder, false);
                        URLImage urlim = URLImage.createToStorage(enc, url,url, URLImage.RESIZE_SCALE);
              addButton(urlim, lst.get(i), lp, res);
              show();
          }
Container cntt = new Container(BoxLayout.y());
Container cnt2 = new Container(BoxLayout.x());
  Label tot = new Label("TOTAL: ");
  Button valider = new Button("valider"); 
  Button retour = new Button("retour à la vitrine");
  computeTotal(lst);
   if (spanier.isEmpty()){
    valider.addActionListener(e -> ToastBar.showMessage("Votre panier est vide!", FontImage.MATERIAL_INFO));        }
   else{
  valider.addActionListener(new ActionListener() {
      
            @Override
            
            public void actionPerformed(ActionEvent evt) {
                   //ajouter commande
   SessionManager sm = new SessionManager();
   int userId = sm.getId();
   //ServiceCommande cs = new ServiceCommande();
   Commande c = new Commande(userId);
   c.setPrix_total(10);
   //cs.ajouterCommande(c);

   //ajouter lignes commandes
                //add in the msg the result of compute total!!!!
                sendMessage("Votre commande est confirmee pour la somme de DT.   Merci de votre confiance!   ", userId);
      
                        Dialog.show("Info", "Commande validée avec succés", new Command("ok"));
                           lst.clear();
                           computeTotal(lst);

                    }
        });
   }
  retour.addActionListener(e -> {afficherVitrine(res);
  });
  cnt2.add(tot);
  cnt2.add(prix_total);
  cntt.add(valider);
  cntt.add(retour);
  add(cnt2);
  add(cntt);
}

      private void addButton(Image img  ,LignePanier i , LignePanier pp, Resources res) {
       int height = Display.getInstance().convertToPixels(13.5f);
       int width = Display.getInstance().convertToPixels(18f);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
       
       Container cnt = BorderLayout.west(image);
       //cnt.setLeadComponent(image);
       Label nomtxt = new Label("Libellé :","NewsTopLine2");
       Label prixtxt = new Label("Prix :","NewsTopLine2");
       Label qtetxt= new Label("Quantité:","NewsTopLine2");
       Slider s = new Slider();
        s.setEditable(true);
        s.setMaxValue(pp.getStock());
        s.setMinValue(1);
        Label sl = new Label("1","NewsTopLine");

        s.addDataChangedListener(new DataChangedListener() {
            @Override
            public void dataChanged(int type, int index) {
                sl.setText(String.valueOf(s.getProgress()));
                        System.out.println(sl.getText());

            }
        });
        sl.setTextPosition(RIGHT);
       Label nom = new Label(String.valueOf(pp.getLibelle()),"NewsTopLine");
       nom.setTextPosition(RIGHT);
       
       Label prix = new Label(String.valueOf(pp.getPrix())+" DT","NewsTopLine");
       prix.setTextPosition(RIGHT);
     
       
             cnt.add(BorderLayout.CENTER,
               BoxLayout.encloseY(
                       BoxLayout.encloseX(nomtxt, nom),
                       BoxLayout.encloseX(prixtxt , prix),
                       BoxLayout.encloseX(qtetxt, s),
                       BoxLayout.encloseX(sl)
               ));
        add(cnt);
        add(createLineSeparator(0x5e99bd));
        add(createLineSeparator(0x5e99bd));
        add(createLineSeparator(0x5e99bd));
             
      }
  
    
   public void sendMessage(String message, int usedId) {
 SessionManager sc = new SessionManager();
  String telephone = "+21658941996";
  NexmoClient client = NexmoClient.builder()
   .apiKey("40553437")
   .apiSecret("lahaz6NIU4VhkhcA")
   .build();

  SmsSubmissionResponse responses = client.getSmsClient()
   .submitMessage(new TextMessage(
    "S4S",
    telephone,
    message
   ));
  for (SmsSubmissionResponseMessage response: responses.getMessages()) {
   System.out.println(response);
  }
 }
    
}


    


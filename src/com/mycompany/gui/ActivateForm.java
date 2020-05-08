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

import com.codename1.components.FloatingHint;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.BaseForm;
import com.mycompany.gui.NewsfeedForm;
import com.sun.mail.smtp.SMTPTransport;
import java.io.IOException;


import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 * Account activation UI
 *
 * @author Shai Almog
 */
public class ActivateForm extends BaseForm {
     TextField email ;
    public ActivateForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        setUIID("IMGLogin");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
       // setUIID("Activate");
        
      
        
    email= new TextField("", "saisir votre email", 20, TextField.ANY);
        email.setSingleLineTextArea(false);
        
        Button signUp = new Button("Valider");
        Label alreadHaveAnAccount = new Label("Retour se connecter?");
        Button signIn = new Button("Renouveler votre mot de passe");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("CenterLink");
        
        Container content = BoxLayout.encloseY(
         new Label(res.getImage("work2.png"), "CenterLabel"),

                new FloatingHint(email),
                createLineSeparator(),
                signUp,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        signUp.requestFocus();
        signUp.addActionListener(e -> {
                 InfiniteProgress ip = new InfiniteProgress();
            final Dialog ipDlg    = ip.showInifiniteBlocking();
                        sendMail();
                            ipDlg.dispose();
                Dialog.show("Mot de passe", "Nous avons envoyé le mot de passe à votre e-mail. Veuillez vérifier votre boîte de réception", new Command("OK"));
                       new SignInForm(res).show();
                       refreshTheme();

        });
    }
    
    public void sendMail() {
                          try {
               
                Properties props = new Properties();
                props.put("mail.transport.protocol", "smtp");
                props.put("mail.smtps.host", "smtp.gmail.com");
                props.put("mail.smtps.auth", "true");
                Session session = Session.getInstance(props, null);
                
                MimeMessage msg = new MimeMessage(session);
                
                msg.setFrom(new InternetAddress("Reintialisation mot de passe <my_email@myDomain.com>"));
                msg.setRecipients(Message.RecipientType.TO, email.getText().toString());
                msg.setSubject("Stock For Speed : Confirmation du  ");
                msg.setSentDate(new Date(System.currentTimeMillis()));
                String x = "chadichadi";
                String txt = "Bienvenue sur S4S : Tapez ce mot de passe : "+x+"  dans le champs requis et appuiez sur Confirmer ";
                
                msg.setText(txt);
                SMTPTransport st = (SMTPTransport)session.getTransport("smtps");
                st.connect("smtp.gmail.com",465,"troudishedy6@gmail.com","programingdeltagramma");
                st.sendMessage(msg, msg.getAllRecipients());
                
                System.out.println("ServerResponse : " + st.getLastServerResponse());
          
            } catch (MessagingException ex) {
                              System.out.println(ex.getMessage()
                              );
            }
              
    }
    
}

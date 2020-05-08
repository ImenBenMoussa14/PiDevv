/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.utils;

/**
 *
 * @author Maryem
 */
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
//import com.mycompany.gui.BaseForm;
//import com.mycompany.gui.NewsfeedForm;
import com.sun.mail.smtp.SMTPTransport;
import java.io.IOException;
import com.mycompany.Entities.Vehicule;
//import com.codename1.io.Properties;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeMessage;
//import javax.swing.JOptionPane;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail 
{
    

    public static void send(String recepient,Vehicule v) throws MessagingException 
    {
        try{
        System.out.println("Preparing Send email");
         Properties props = new Properties();
                props.put("mail.transport.protocol", "smtp");
                props.put("mail.smtps.host", "smtp.gmail.com");
                props.put("mail.smtps.auth", "true");
                
        String myAccountEmail ="s4sb.tobeornottobe@gmail.com";
        String password ="tobeornottobe";
       Session session = Session.getInstance(props, null);
       
   
           
    

       
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Confirmation Location");
            message.setText("Vous avez bien loueé la vehicule du matricule "
                    + v.getMat()+" de la marque"+v.getMarque()+" répondez par "
                     + "un mail de confirmation si vous voulez confirmer"
                     + "votre loction   NB: Le vous douvez payer chaque semaine une somme de "+v.getPrix()+"Dt");
        
                SMTPTransport st = (SMTPTransport)session.getTransport("smtps");
                st.connect("smtp.gmail.com",465,myAccountEmail,password);
                st.sendMessage(message, message.getAllRecipients());
                 System.out.println("message sent");
                System.out.println("ServerResponse : " + st.getLastServerResponse());
          
            } catch (MessagingException ex) {
                              System.out.println(ex.getMessage()
                              );
            }
    }}

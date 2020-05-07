/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Chédy
 */
public class HomeForm extends Form {
    Form current;
    public HomeForm() {
        current =this;
        setTitle("Home");
        setLayout(BoxLayout.y());
        add(new Label("choose an option"));
        Button btnAddReclamation =new Button("Add Reclamation");
        Button btnListReclamation = new Button("List Reclamation");
        
       // btnAddReclamation.addActionListener(e-> new AjouterFeedbackForm(current).show());
        //btnListReclamation.addActionListener(e-> new ListReclamationForm(current).show());
        addAll(btnAddReclamation,btnListReclamation);
        
        
        
        
    }


    
    
    
}

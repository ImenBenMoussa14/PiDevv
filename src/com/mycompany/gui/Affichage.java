/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.MultipartRequest;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.Entities.Reclamation;

/**
 *
 * @author Chédy
 */
public class Affichage extends Form{
    SpanLabel lb;
    Form f;
    Container container1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    MultipartRequest request = new MultipartRequest();
        Image img;
    ImageViewer imgv;
    EncodedImage enc;
    public Affichage(Resources theme) {
                
        Container container1All = new Container(new BoxLayout(BoxLayout.Y_AXIS));

  
        
    }

    public Container getC() {
        return container1;
    }

    public void setF(Container c) {
        this.container1 = c;
    }
      public Form getF() {
        return f;
    }

    
}

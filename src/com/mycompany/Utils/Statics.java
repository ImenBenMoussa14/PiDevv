/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.Utils;

import com.mycompany.Entities.Utilisateur;


public class Statics {
       public static final String BASE_URL="http://localhost/projet_symfony/final/test/web/app_dev.php/api/mobile/";
       
        public static Utilisateur currentUser=new Utilisateur(14);
 //   public static Utilisateur currentUser=new Utilisateur(SessionManager.getId());
}



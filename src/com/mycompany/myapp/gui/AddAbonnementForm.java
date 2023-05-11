/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Abonnement;
import com.mycompany.myapp.services.ServiceAbonnement;


/**
 *
 * @author Ace River
 */
public class AddAbonnementForm  extends BaseForm {
    
      public AddAbonnementForm(Form previous) {
            setUIID("SignIn"); // add this line
        setTitle("Add a new Abonnement");
        setLayout(BoxLayout.y());
        
        TextField tfNom = new TextField("", "Nom", 20, TextField.ANY);
        
        TextField tfdescription = new TextField("", "description", 20, TextField.ANY);
        TextField tfprix = new TextField("", "prix", 20, TextField.ANY);
       
      
        Button btnValider = new Button("Add Abonnement");
           String imageURL = "http://localhost/sportify/public/uploads/bozo-63ef0e0a0f152-640134765fe25.png";

    EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(300, 300, 0xffff0000), true);
    URLImage image = URLImage.createToStorage(placeholder, "yoyo", imageURL, URLImage.RESIZE_SCALE_TO_FILL);
         ImageViewer imageViewer = new ImageViewer(image);
          add(imageViewer);
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfNom.getText().length() == 0  || tfdescription.getText().length() == 0 || tfprix.getText().length() == 0 ) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        Abonnement e = new Abonnement();
                        e.setNom(tfNom.getText());
                        e.setDescription(tfdescription.getText());
                        e.setPrix(Integer.parseInt(tfprix.getText()));
                       
                        
                        if (ServiceAbonnement.getInstance().addAbonnement(e)) {
                           Dialog.show("Success", "Abonnement added successfully", new Command("OK"));
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "prix must be a number", new Command("OK"));
                         Dialog.show("ERROR", "pattern:\"/^[A-Z]/\",\n" +
"            message:\"The first letter of the string must be uppercase\")]", new Command("OK"));
                    }
                }
            }
        });
        
        addAll(tfNom, tfdescription, tfprix,  btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
                
    
    

}

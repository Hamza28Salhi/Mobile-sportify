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
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.services.ServiceEvenement;


/**
 *
 * @author HOUYEM
 */
public class AddEvenementForm extends BaseForm{
    public AddEvenementForm(Form previous) {
         setUIID("SignIn"); // add this line
        setTitle("Add a new Evenement");
        setLayout(BoxLayout.y());
        
        //TextField tfDate = new TextField("", "Date", 20, TextField.ANY);
        
        TextField tfType = new TextField("", "type", 20, TextField.ANY);
        TextField tfLieu = new TextField("", "lieu", 20, TextField.ANY);
        TextField tfDescription = new TextField("", "description", 20, TextField.ANY);
        TextField tfEvenPic = new TextField("", "evenpic", 20, TextField.ANY);
        TextField tfTitre = new TextField("", "titre", 20, TextField.ANY);
        
        
        Button btnValider = new Button("Add Evenement");
          /* String imageURL = "http://localhost/sportify/public/uploads/pantalons-63f54e54d289c.png";

    EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(300, 300, 0xffff0000), true);
    URLImage image = URLImage.createToStorage(placeholder, "yoyo", imageURL, URLImage.RESIZE_SCALE_TO_FILL);
         ImageViewer imageViewer = new ImageViewer(image);
          add(imageViewer);*/
          
          
         btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ( tfLieu.getText().length() == 0 || tfDescription.getText().length() == 0 || tfEvenPic.getText().length() == 0 || tfTitre.getText().length() == 0 ) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        Evenement e = new Evenement();
                        //e.setDate(tfDate.getText());
                         
                        e.setType(tfType.getText());                      
                        e.setLieu(tfLieu.getText());
                        e.setDescription(tfDescription.getText());
                         e.setEvenPic(tfEvenPic.getText());
                       e.setTitre(tfTitre.getText());
                        
                        if (ServiceEvenement.getInstance().addEvenement(e)) {
                           Dialog.show("Success", "Evenement added successfully", new Command("OK"));
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Quantite must be a number", new Command("OK"));
                    }
                }
            }
        });
         addAll(tfType, tfLieu, tfDescription, tfEvenPic, tfTitre, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, p-> previous.showBack());
    }
    
    
}

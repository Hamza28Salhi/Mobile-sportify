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
import com.mycompany.myapp.entities.Equipe;
import com.mycompany.myapp.services.ServiceEquipe;


/**
 *
 * @author Ace River
 */
public class AddEquipeForm  extends BaseForm {
    
      public AddEquipeForm(Form previous) {
            setUIID("SignIn"); // add this line
        setTitle("Add a new Equipe");
        setLayout(BoxLayout.y());
        
        TextField tfName = new TextField("", "Nom", 20, TextField.ANY);
        
        TextField tfjoueurs = new TextField("", "joueurs", 20, TextField.ANY);
        TextField tfclassement = new TextField("", "classement", 20, TextField.ANY);
        TextField tfentraineur = new TextField("", "entraineur", 20, TextField.ANY);
        TextField tfcategorie = new TextField("", "categorie", 20, TextField.ANY);
      
        Button btnValider = new Button("Add Equipe");
           String imageURL = "http://localhost/sportify/public/uploads/bozo-63ef0e0a0f152-640134765fe25.png";

    EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(300, 300, 0xffff0000), true);
    URLImage image = URLImage.createToStorage(placeholder, "yoyo", imageURL, URLImage.RESIZE_SCALE_TO_FILL);
         ImageViewer imageViewer = new ImageViewer(image);
          add(imageViewer);
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfName.getText().length() == 0 || tfjoueurs.getText().length() == 0 || tfclassement.getText().length() == 0 || tfentraineur.getText().length() == 0 || tfcategorie.getText().length() == 0) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        Equipe e = new Equipe();
                        e.setNom(tfName.getText());
                        e.setJoueurs(tfjoueurs.getText());
                        e.setClassement(Integer.parseInt(tfclassement.getText()));
                        e.setEntraineur(tfentraineur.getText());
                        e.setCategorie(tfcategorie.getText());
                        
                        if (ServiceEquipe.getInstance().addEquipe(e)) {
                           Dialog.show("Success", "Equipe added successfully", new Command("OK"));
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Classement must be a number", new Command("OK"));
                    }
                }
            }
        });
        
        addAll(tfName, tfjoueurs, tfclassement, tfentraineur, tfcategorie, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
                
    
    

}

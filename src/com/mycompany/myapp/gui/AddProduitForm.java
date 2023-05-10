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
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.services.ServiceProduit;

/**
 *
 * @author lenovo
 */
public class AddProduitForm extends BaseForm {
    
      public AddProduitForm(Form previous) {
            setUIID("SignIn"); // add this line
        setTitle("Add a new Produit");
        setLayout(BoxLayout.y());
        
        TextField tfName = new TextField("", "Nom", 20, TextField.ANY);
        
        TextField tfprix = new TextField("", "prixProduit", 20, TextField.ANY);
        TextField tfmarque = new TextField("", "marqueProduit", 20, TextField.ANY);
        TextField tfimage = new TextField("", "image", 20, TextField.ANY);
        TextField tfquantite = new TextField("", "quantite", 20, TextField.ANY);
        
        
        Button btnValider = new Button("Add Produit");
           String imageURL = "http://localhost/sportify/public/uploads/pantalons-63f54e54d289c.png";

    EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(300, 300, 0xffff0000), true);
    URLImage image = URLImage.createToStorage(placeholder, "yoyo", imageURL, URLImage.RESIZE_SCALE_TO_FILL);
         ImageViewer imageViewer = new ImageViewer(image);
          add(imageViewer);
          
          
         btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfName.getText().length() == 0 || tfprix.getText().length() == 0 || tfimage.getText().length() == 0 || tfmarque.getText().length() == 0 || tfquantite.getText().length() == 0 ) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        Produit p = new Produit();
                        p.setNomProduit(tfName.getText());
                       
                        p.setPrixProduit(Double.parseDouble(tfprix.getText()));
                        p.setMarqueProduit(tfmarque.getText());
                         p.setImage(tfimage.getText());
                       p.setQuantite(Integer.parseInt(tfquantite.getText()));
                        
                        if (ServiceProduit.getInstance().addProduit(p)) {
                           Dialog.show("Success", "Produit added successfully", new Command("OK"));
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException p) {
                        Dialog.show("ERROR", "Quantite must be a number", new Command("OK"));
                    }
                }
            }
        });
         addAll(tfName, tfmarque, tfprix, tfimage, tfquantite, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, p-> previous.showBack());
    }
    
        
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.services.ServiceEvenement;
import java.util.ArrayList;

/**
 *
 * @author HOUYEM
 */
public class ListEvenementsForm extends BaseForm {
    
    
    
  
    public ListEvenementsForm(Form previous) {
    setTitle("List Evenement");
    setLayout(BoxLayout.y());
    setUIID("SignIn"); // add this line

    ArrayList<Evenement> tasks = ServiceEvenement.getInstance().getAllEvenement();
    for (Evenement e : tasks) {
        addElement(e);
    }

    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, p -> previous.showBack());

}
    
public void addElement(Evenement evenement) {
    Container c = new Container(new GridLayout(1, 2));
    String type= evenement.getType();
    

    // Load the image using URLImage
    //String imageURL = "http://localhost/sportify/public/uploads/" + produit.getImage();

    /*EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(300, 300, 0xffff0000), true);
    URLImage image = URLImage.createToStorage(placeholder, evenement.getImage(), imageURL, URLImage.RESIZE_SCALE_TO_FILL);
    ImageViewer imageViewer = new ImageViewer(image);
    c.add(imageViewer);*/

    Label nomLabel = new Label(evenement.getType());
    
Style style = nomLabel.getAllStyles();
style.setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
style.setFgColor(0xff0000); // sets foreground color to red
    nomLabel.addPointerPressedListener(evt -> {
        Dialog popup = new Dialog();
        popup.setLayout(new BorderLayout());
        
 // Set the background color of the popup
        popup.getContentPane().getAllStyles().setBgColor(0xAAAAAA);

        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        container.setUIID("Popup");
        
        
        
        
       Label lieuLabel = new Label("lieu:");
        lieuLabel.getAllStyles().setFgColor(0xff0000); // set the foreground color of the label
        container.add(lieuLabel);

        Label descripionLabel=  new Label(evenement.getDescription());
        descripionLabel.getAllStyles().setFgColor(0xff0000); // set the foreground color of the label
        container.add(descripionLabel);

      
            
        
        
 Label titreLabel = new Label("titre:");
        titreLabel.getAllStyles().setFgColor(0xff0000); // set the foreground color of the label
        container.add(titreLabel);

        
        

   // add delete button
        Button deleteBtn = new Button("Delete");
        deleteBtn.addActionListener(p -> {
            ServiceEvenement.getInstance().deleteEvenement(evenement.getId());
            popup.dispose();
            refreshList();
        });
        container.add(deleteBtn);
        
  

        popup.add(BorderLayout.CENTER, container);
        popup.showPopupDialog(lieuLabel);
    });

    c.add(nomLabel);

    c.setLeadComponent(nomLabel);
    add(c);
}

void refreshList() {
    this.removeAll();
    ArrayList<Evenement> evenements = ServiceEvenement.getInstance().getAllEvenement();
    for (Evenement e : evenements) {
        addElement(e);
    }
    this.revalidate();
}



}
    
    
    


    

    
    








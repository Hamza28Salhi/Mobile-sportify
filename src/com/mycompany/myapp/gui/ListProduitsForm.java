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
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.services.ServiceProduit;
import java.util.ArrayList;

/**
 *
 * @author lenovo
 */
public class ListProduitsForm extends BaseForm {
  
    public ListProduitsForm(Form previous) {
    setTitle("List Produit");
    setLayout(BoxLayout.y());
    setUIID("SignIn"); // add this line

    ArrayList<Produit> tasks = ServiceProduit.getInstance().getAllProduits();
    for (Produit p : tasks) {
        addElement(p);
    }

    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, p -> previous.showBack());

}
    
public void addElement(Produit produit) {
    Container c = new Container(new GridLayout(1, 2));
    int quantite = produit.getQuantite();

    // Load the image using URLImage
    String imageURL = "http://localhost/sportify/public/uploads/" + produit.getImage();

    EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(300, 300, 0xffff0000), true);
    URLImage image = URLImage.createToStorage(placeholder, produit.getImage(), imageURL, URLImage.RESIZE_SCALE_TO_FILL);
    ImageViewer imageViewer = new ImageViewer(image);
    c.add(imageViewer);

    Label nomLabel = new Label(produit.getNomProduit());
    
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
        
        
        
        
       Label marqueLabel = new Label("Marque:");
        marqueLabel.getAllStyles().setFgColor(0xff0000); // set the foreground color of the label
        container.add(marqueLabel);

        Label marqueValue = new Label(produit.getMarqueProduit());
        marqueValue.getAllStyles().setFgColor(0xff0000); // set the foreground color of the label
        container.add(marqueValue);

      
            
        
        
 Label quantiteLabel = new Label("Quantite:");
        quantiteLabel.getAllStyles().setFgColor(0xff0000); // set the foreground color of the label
        container.add(quantiteLabel);

        Label quantiteValue = new Label(String.valueOf(quantite));
        quantiteValue.getAllStyles().setFgColor(0xff0000); // set the foreground color of the label
        container.add(quantiteValue);
        

   // add delete button
        Button deleteBtn = new Button("Delete");
        deleteBtn.addActionListener(p -> {
            ServiceProduit.getInstance().deleteProduit(produit.getId());
            popup.dispose();
            refreshList();
        });
        container.add(deleteBtn);
        
  

        popup.add(BorderLayout.CENTER, container);
        popup.showPopupDialog(nomLabel);
    });

    c.add(nomLabel);

    c.setLeadComponent(nomLabel);
    add(c);
}

void refreshList() {
    this.removeAll();
    ArrayList<Produit> produits = ServiceProduit.getInstance().getAllProduits();
    for (Produit p : produits) {
        addElement(p);
    }
    this.revalidate();
}



}
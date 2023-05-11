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
import com.mycompany.myapp.entities.Abonnement;
import com.mycompany.myapp.services.ServiceAbonnement;
import java.util.ArrayList;
/**
 *
 * @author Ace River
 */
public class ListAbonnementForm extends BaseForm {
  
    public ListAbonnementForm(Form previous) {
    setTitle("List Abonnement");
    setLayout(BoxLayout.y());
    setUIID("SignIn"); // add this line

    ArrayList<Abonnement> tasks = ServiceAbonnement.getInstance().getAllAbonnement();
    for (Abonnement e : tasks) {
        addElement(e);
    }

    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

}


public void addElement(Abonnement abonnement) {
    Container c = new Container(new GridLayout(1, 2));
    int prix = abonnement.getPrix();

    // Load the image using URLImage
   

   

    Label nomLabel = new Label(abonnement.getNom());
 
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
        Label descriptionLabel = new Label("Liste des description:");
        descriptionLabel.getAllStyles().setFgColor(0xff0000); // set the foreground color of the label
        container.add(descriptionLabel);

        Label descriptionValue = new Label(abonnement.getDescription());
        descriptionValue.getAllStyles().setFgColor(0xff0000); // set the foreground color of the label
        container.add(descriptionValue);

        Label prixLabel = new Label("prix:");
        prixLabel.getAllStyles().setFgColor(0xff0000); // set the foreground color of the label
        container.add(prixLabel);

        Label prixValue = new Label(String.valueOf(prix));
        prixValue.getAllStyles().setFgColor(0xff0000); // set the foreground color of the label
        container.add(prixValue);

        Label NomLabel = new Label("nom:");
        nomLabel.getAllStyles().setFgColor(0xff0000); // set the foreground color of the label
        container.add(nomLabel);

        Label nomValue = new Label(abonnement.getNom());
        nomValue.getAllStyles().setFgColor(0xff0000); // set the foreground color of the label
        container.add(nomValue);

    Button deleteBtn = new Button("Delete");
        deleteBtn.addActionListener(p -> {
            ServiceAbonnement.getInstance().deleteAbonnement(abonnement.getId());
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
    ArrayList<Abonnement> abonnements = ServiceAbonnement.getInstance().getAllAbonnement();
    for (Abonnement e : abonnements) {
        addElement(e);
    }
    this.revalidate();
}



}

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
import com.mycompany.myapp.entities.Equipe;
import com.mycompany.myapp.services.ServiceEquipe;
import java.util.ArrayList;
/**
 *
 * @author Ace River
 */
public class ListEquipesForm extends BaseForm {
  
    public ListEquipesForm(Form previous) {
    setTitle("List Equipe");
    setLayout(BoxLayout.y());
    setUIID("SignIn"); // add this line

    ArrayList<Equipe> tasks = ServiceEquipe.getInstance().getAllEquipes();
    for (Equipe e : tasks) {
        addElement(e);
    }

    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

}


public void addElement(Equipe equipe) {
    Container c = new Container(new GridLayout(1, 2));
    int classement = equipe.getClassement();

    // Load the image using URLImage
    String imageURL = "http://localhost/sportify/public/uploads/" + equipe.getPicture();

    EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(300, 300, 0xffff0000), true);
    URLImage image = URLImage.createToStorage(placeholder, equipe.getPicture(), imageURL, URLImage.RESIZE_SCALE_TO_FILL);
    ImageViewer imageViewer = new ImageViewer(image);
    c.add(imageViewer);

    Label nomLabel = new Label(equipe.getNom());
 
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
        Label joueursLabel = new Label("Liste des Joueurs:");
        joueursLabel.getAllStyles().setFgColor(0xff0000); // set the foreground color of the label
        container.add(joueursLabel);

        Label joueursValue = new Label(equipe.getJoueurs());
        joueursValue.getAllStyles().setFgColor(0xff0000); // set the foreground color of the label
        container.add(joueursValue);

        Label classementLabel = new Label("Classement:");
        classementLabel.getAllStyles().setFgColor(0xff0000); // set the foreground color of the label
        container.add(classementLabel);

        Label classementValue = new Label(String.valueOf(classement));
        classementValue.getAllStyles().setFgColor(0xff0000); // set the foreground color of the label
        container.add(classementValue);

        Label entraineurLabel = new Label("Entraineur:");
        entraineurLabel.getAllStyles().setFgColor(0xff0000); // set the foreground color of the label
        container.add(entraineurLabel);

        Label entraineurValue = new Label(equipe.getEntraineur());
        entraineurValue.getAllStyles().setFgColor(0xff0000); // set the foreground color of the label
        container.add(entraineurValue);

        Label categorieLabel = new Label("Categorie:");
        categorieLabel.getAllStyles().setFgColor(0xff0000); // set the foreground color of the label
        container.add(categorieLabel);

        Label categorieValue = new Label(equipe.getCategorie());
        categorieValue.getAllStyles().setFgColor(0xff0000); // set the foreground color of the label
        container.add(categorieValue);

        // add delete button
        Button deleteBtn = new Button("Delete");
        deleteBtn.addActionListener(e -> {
            ServiceEquipe.getInstance().deleteEquipe(equipe.getId());
            popup.dispose();
            refreshList();
        });
        container.add(deleteBtn);

        // add update button
        Button updateBtn = new Button("Update");
        updateBtn.addActionListener(e -> {
            new EditEquipeForm(this, equipe).show();
            popup.dispose();
        });
        container.add(updateBtn);

        popup.add(BorderLayout.CENTER, container);
        popup.showPopupDialog(nomLabel);
    });

    c.add(nomLabel);

    c.setLeadComponent(nomLabel);
    add(c);
}


void refreshList() {
    this.removeAll();
    ArrayList<Equipe> equipes = ServiceEquipe.getInstance().getAllEquipes();
    for (Equipe e : equipes) {
        addElement(e);
    }
    this.revalidate();
}



}

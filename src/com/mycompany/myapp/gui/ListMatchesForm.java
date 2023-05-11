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
import com.mycompany.myapp.entities.Matches;
import com.mycompany.myapp.services.ServiceMatches;
import java.util.ArrayList;
/**

/**
 *
 * @author Ace River
 */
public class ListMatchesForm extends BaseForm {
     public ListMatchesForm(Form previous) {
    setTitle("List Matches");
    setLayout(BoxLayout.y());
    setUIID("SignIn"); // add this line

    ArrayList<Matches> tasks = ServiceMatches.getInstance().getAllMatches();
    for (Matches e : tasks) {
        addElement(e);
    }

    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

}
     public void addElement(Matches match) {
    Container c = new Container(new GridLayout(1, 2));
    int Id = match.getId();

  
    Label nomLabel = new Label(match.getNom());
 
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
        Label stadeLabel = new Label(" Stade:");
        stadeLabel.getAllStyles().setFgColor(0xff0000); // set the foreground color of the label
        container.add(stadeLabel);

        Label stadeValue = new Label(match.getStade());
        stadeValue.getAllStyles().setFgColor(0xff0000); // set the foreground color of the label
        container.add(stadeValue);
        
        Label latitudeLabel = new Label("Latitude:");
        latitudeLabel.getAllStyles().setFgColor(0xff0000);
        container.add(latitudeLabel);

        Label latitudeValue = new Label(Float.toString(match.getLatitude()));
        latitudeValue.getAllStyles().setFgColor(0xff0000);
        container.add(latitudeValue);
        
        

        // add delete button
        Button deleteBtn = new Button("Delete");
        deleteBtn.addActionListener(e -> {
            ServiceMatches.getInstance().deleteMatches(match.getId());
            popup.dispose();
            refreshList();
        });
        container.add(deleteBtn);

        // add update button
        Button updateBtn = new Button("Update");
        updateBtn.addActionListener(e -> {
           // new EditEquipeForm(this, equipe).show();
            popup.dispose();
        });
        container.add(updateBtn);
        // add map button
Button mapButton = new Button("Map");
mapButton.addActionListener(e -> {
    // open a new form for the map with the latitude and longitude values
    new MapForm(match.getLatitude(), match.getLongitude()).show();
});
container.add(mapButton);

        popup.add(BorderLayout.CENTER, container);
        popup.showPopupDialog(nomLabel);
    });

    c.add(nomLabel);

    c.setLeadComponent(nomLabel);
    add(c);
}

    private void refreshList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}

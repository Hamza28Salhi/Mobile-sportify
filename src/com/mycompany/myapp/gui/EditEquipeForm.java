/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Equipe;
import com.mycompany.myapp.services.ServiceEquipe;

import com.mycompany.myapp.entities.Equipe;

/**
 *
 * @author Ace River
 */
public class EditEquipeForm extends BaseForm {
    
    private final Equipe equipe;
    private final ListEquipesForm listEquipesForm;
    private final TextField nomTextField;
    private final TextField joueursTextField;
    private final TextField classementTextField;
    private final TextField entraineurTextField;
    private final TextField categorieTextField;
    private final Button updateButton;

   
    public EditEquipeForm(ListEquipesForm listEquipesForm, Equipe equipe) {
           setUIID("SignIn"); // add this line
        this.equipe = equipe;
        this.listEquipesForm = listEquipesForm;
        setTitle("Edit Equipe");
        setLayout(BoxLayout.y());

        nomTextField = new TextField(equipe.getNom(), "Nom");
        joueursTextField = new TextField(equipe.getJoueurs(), "Joueurs");
        classementTextField = new TextField(String.valueOf(equipe.getClassement()), "Classement");
        entraineurTextField = new TextField(equipe.getEntraineur(), "Entraineur");
        categorieTextField = new TextField(equipe.getCategorie(), "Categorie");

        updateButton = new Button("Update");

        updateButton.addActionListener(e -> {
            if (nomTextField.getText().isEmpty() || joueursTextField.getText().isEmpty() ||
                    classementTextField.getText().isEmpty() || entraineurTextField.getText().isEmpty() ||
                    categorieTextField.getText().isEmpty()) {
                Dialog.show("Error", "Please fill in all fields", "OK", null);
                return;
            }

            Equipe updatedEquipe = new Equipe(
                    equipe.getId(),
                    nomTextField.getText(),
                    joueursTextField.getText(),
                    Integer.parseInt(classementTextField.getText()),
                    entraineurTextField.getText(),
                    categorieTextField.getText()
            );

            ServiceEquipe.getInstance().updateEquipe(updatedEquipe);

            listEquipesForm.refreshList();
            this.dispose();
        });

        Container formContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        formContainer.add(nomTextField);
        formContainer.add(joueursTextField);
        formContainer.add(classementTextField);
        formContainer.add(entraineurTextField);
        formContainer.add(categorieTextField);

        ComponentGroup cg = new ComponentGroup();
        cg.addComponent(updateButton);

       Container centerContainer = new Container(new BorderLayout());
centerContainer.addComponent(BorderLayout.CENTER, formContainer);

Container southContainer = new Container(new BorderLayout());
southContainer.addComponent(BorderLayout.CENTER, cg);

this.setLayout(new BorderLayout());
this.addComponent(BorderLayout.CENTER, centerContainer);
this.addComponent(BorderLayout.SOUTH, southContainer);

    }

    public void show() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void dispose() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

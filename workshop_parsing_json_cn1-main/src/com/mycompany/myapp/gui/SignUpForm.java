/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.mycompany.myapp.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceUser;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.Dialog;
import com.codename1.ui.Command;


/**
 * Signup UI
 *
 * @author Shai Almog
 */
public class SignUpForm extends BaseForm {

    public SignUpForm(Resources res) {
        
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
                
        // create UI components for full name, email, address and password
        TextField fullNameField = new TextField("", "Full Name", 20, TextField.ANY);
        TextField emailField = new TextField("", "E-Mail", 20, TextField.EMAILADDR);
        emailField.setConstraint(TextField.EMAILADDR);
        
        TextField addressField = new TextField("", "Address", 20, TextField.ANY);
        TextField passwordField = new TextField("", "Password", 20, TextField.PASSWORD);
        
        Button btnValider = new Button("Add User");
        Button next = new Button("Next");
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("Link");
        Label alreadyHaveAnAccount = new Label("Already have an account?");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (fullNameField.getText().length() == 0 || emailField.getText().length() == 0 || addressField.getText().length() == 0 || passwordField.getText().length() == 0 ) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        User u = new User();
                        u.setFullName(fullNameField.getText());
                        u.setEmail(emailField.getText());
                        u.setAddress(addressField.getText());
                        u.setPassword(passwordField.getText());
                        
                        if (ServiceUser.getInstance().addUser(u)) {
                           Dialog.show("Success", "User Registered Successfully", new Command("OK"));
                           new SignInForm(res).show();
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Classement must be a number", new Command("OK"));
                    }
                }
            }
        });
        
        Container contentPane = new Container(new BorderLayout());
        
        contentPane.add(BorderLayout.CENTER, BoxLayout.encloseY(
                fullNameField,
                emailField,
                addressField,
                passwordField,
                btnValider
        ));
        
        contentPane.add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next
        ));
        
        add(contentPane);
        next.requestFocus();
        next.addActionListener(e -> new ActivateForm(res).show());
    }
    
}

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

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.gui.SessionManager;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.mycompany.myapp.services.ServiceUser;

/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class ProfileForm extends BaseForm {

    public ProfileForm(Resources res) {
    super("Newsfeed", BoxLayout.y());
    Toolbar tb = new Toolbar(true);
    setToolbar(tb);
    getTitleArea().setUIID("Container");
    setTitle("Profile");
    getContentPane().setScrollVisible(false);

    super.addSideMenu(res);

    tb.addSearchCommand(e -> {});

    // Retrieve user data from the session
    SessionManager session = new SessionManager();
    String fullname = session.getFullName();
    String email = session.getEmail();
    String address = session.getAddress();

    Image img = res.getImage("profile-background.jpg");
    if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
        img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
    }
    ScaleImageLabel sl = new ScaleImageLabel(img);
    sl.setUIID("BottomPad");
    sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

    Label facebook = new Label("786 followers", res.getImage("facebook-logo.png"), "BottomPad");
    Label twitter = new Label("486 followers", res.getImage("twitter-logo.png"), "BottomPad");
    facebook.setTextPosition(BOTTOM);
    twitter.setTextPosition(BOTTOM);

    add(LayeredLayout.encloseIn(
            sl,
            BorderLayout.south(
                    GridLayout.encloseIn(3,
                            facebook,
                            FlowLayout.encloseCenter(
                                    new Label(res.getImage("profile-pic.jpg"), "PictureWhiteBackgrond")),
                            twitter
                    )
            )
    ));
    

    // Set the retrieved user data in the text fields
    TextField fullNameField = new TextField(fullname);
    fullNameField.setUIID("TextFieldBlack");
    addStringValue("Full Name", fullNameField);

    TextField emailField = new TextField(email, "E-Mail", 20, TextField.EMAILADDR);
    emailField.setUIID("TextFieldBlack");
    addStringValue("E-Mail", emailField);

    TextField addressField = new TextField(address);
    addressField.setUIID("TextFieldBlack");
    addStringValue("Address", addressField);

    CheckBox cb1 = CheckBox.createToggle(res.getImage("on-off-off.png"));
    cb1.setUIID("Label");
    cb1.setPressedIcon(res.getImage("on-off-on.png"));
    CheckBox cb2 = CheckBox.createToggle(res.getImage("on-off-off.png"));
    cb2.setUIID("Label");
    cb2.setPressedIcon(res.getImage("on-off-on.png"));

    addStringValue("Facebook", FlowLayout.encloseRightMiddle(cb1));
    addStringValue("Twitter", FlowLayout.encloseRightMiddle(cb2));
    
    Button deleteButton = new Button("Delete Account");
    deleteButton.addActionListener((evt) -> {
    int userId = SessionManager.getId(); // assuming you're using SessionManager to store the current user's ID
    boolean success = ServiceUser.getInstance().deleteUser(userId);
    if (success) {
        Dialog.show("Success", "User deleted successfully", "OK", null);
        // handle successful deletion (e.g. go back to login screen)
        new SignInForm(res).show();
    } else {
        Dialog.show("Error", "Failed to delete user", "OK", null);
    }
});
    add(deleteButton);
}
    
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}

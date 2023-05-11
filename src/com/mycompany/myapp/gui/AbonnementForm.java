package com.mycompany.myapp.gui;

import com.codename1.components.FloatingHint;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

/**
 * A form for displaying team information.
 */public class AbonnementForm extends BaseForm {
    public AbonnementForm(Form previous) {
        super(new BorderLayout());

        if (!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout) getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
                getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        }

        getTitleArea().setUIID("Container");
        setUIID("SignIn");

        // Add team image
        String imageURL = "http://localhost/sportify/public/uploads/teswiraequipa.png";
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(500, 500, 0xffff0000), true);
        URLImage image = URLImage.createToStorage(placeholder, "skr", imageURL, URLImage.RESIZE_SCALE_TO_FILL);
        ImageViewer imageViewer = new ImageViewer(image);
        Button teamButton = new Button("View Teams");
        teamButton.addActionListener(e -> new ListAbonnementForm(this).show());
        Container teamContainer = BorderLayout.center(imageViewer).add(BorderLayout.SOUTH, teamButton);
        add(BorderLayout.NORTH, teamContainer);

        // Add cup image
        String cupImageURL = "http://localhost/sportify/public/uploads/Cup.png";
        EncodedImage cupPlaceholder = EncodedImage.createFromImage(Image.createImage(500 ,500, 0xffff0000), true);
        URLImage cupImage = URLImage.createToStorage(cupPlaceholder, "bomba", cupImageURL, URLImage.RESIZE_SCALE_TO_FILL);
        ImageViewer cupImageViewer = new ImageViewer(cupImage);
        Button cupButton = new Button("View Cup");
        cupButton.addActionListener(e -> new ClassementForm(this).show());
        Container cupContainer = BorderLayout.center(cupImageViewer).add(BorderLayout.SOUTH, cupButton);
        add(BorderLayout.SOUTH, cupContainer);
        
        // Add matches image
        String matchesImageURL = "http://localhost/sportify/public/uploads/za3ma.png";
        EncodedImage matchesPlaceholder = EncodedImage.createFromImage(Image.createImage(300 ,300, 0xffff0000), true);
        URLImage matchesImage = URLImage.createToStorage(matchesPlaceholder, "bzaz", matchesImageURL, URLImage.RESIZE_SCALE_TO_FILL);
        ImageViewer matchesImageViewer = new ImageViewer(matchesImage);
        Button categorieButton = new Button("View Categorie");
        categorieButton.addActionListener(e -> new ClassementForm(this).show());
        Container matchesContainer = BorderLayout.center(matchesImageViewer).add(BorderLayout.SOUTH, categorieButton);
        add(BorderLayout.CENTER, matchesContainer);
    }
    
}

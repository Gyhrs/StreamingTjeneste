package com.langesokker;

import com.langesokker.components.ErrorPopup;
import com.langesokker.controllers.GUIController;
import com.langesokker.controllers.MediaController;

import javax.swing.*;
import java.net.URISyntaxException;

public class Main {

    //Main Method that runs the entire project
    public static void main(String[] args) {

        try {
            MediaController.getInstance().loadAllMediaTypes();
        } catch (URISyntaxException e) {
            new ErrorPopup(new JFrame(), "Failed load", "Failed loading program", true);
            return;
        }
        GUIController guicontroller = GUIController.getInstance();
        guicontroller.setupGUI();
        guicontroller.display();
    }

}

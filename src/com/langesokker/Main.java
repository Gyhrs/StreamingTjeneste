package com.langesokker;

import com.langesokker.components.ErrorPopup;
import com.langesokker.controllers.GUIController;
import com.langesokker.controllers.MediaController;

import javax.swing.*;
import java.net.URISyntaxException;

public class Main {

    /**
     * Main Method that runs the entire project
     * @param args starts argumenter
     */
    public static void main(String[] args) {
        MediaController.getInstance().loadAllMediaTypes();
        GUIController guicontroller = GUIController.getInstance();
        guicontroller.setupGUI();
        guicontroller.display();
    }

}

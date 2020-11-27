package com.langesokker;

public class Main {

    //Main Method that runs the entire project
    public static void main(String[] args) {
        GUIController guicontroller = GUIController.getInstance();
        guicontroller.setupGUI();
        guicontroller.display();
    }

}

package com.langesokker;

import javax.swing.*;
import java.awt.*;

public class GUIController {
    private static GUIController instance;
    public JFrame frame;
    public GUIController(){
        frame = new JFrame("Lange sokker streaming");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static GUIController getInstance(){
            if(instance == null) {
                    instance = new GUIController();
            }
            return instance;

    }

    public void display(){
        frame.pack();
        frame.setVisible(true);
    }

    public void setupGUI(){
        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        Container topContainer = new Container();
        topContainer.setLayout(new BoxLayout(topContainer, BoxLayout.PAGE_AXIS));
        Container contentContainer = new Container();

        JTextField searchbar = new JTextField();
        topContainer.add(searchbar);


        JButton searchButton = new JButton("search");
        topContainer.add(searchButton);

        mainPanel.add(topContainer);

        frame.add(mainPanel);
    }
}

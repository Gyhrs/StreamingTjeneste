package com.langesokker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        mainPanel.setLayout(new BorderLayout());
        Container topContainer = new Container();
        topContainer.setLayout(new GridLayout(1,2));
        Container contentContainer = new Container();
        contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.PAGE_AXIS));

        JTextField searchbar = new JTextField();
        topContainer.add(searchbar);


        JButton searchButton = new JButton("Search");
        topContainer.add(searchButton);

        JButton someButton = new JButton("homdog");
        someButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showConfirmDialog(frame, "HOMDOG!!!!");
            }
        });
        contentContainer.add(someButton);

        mainPanel.add(topContainer, BorderLayout.NORTH);
        mainPanel.add(contentContainer, BorderLayout.CENTER);
        frame.add(mainPanel);
    }
}

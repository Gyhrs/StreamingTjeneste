package com.langesokker.controllers;

import com.langesokker.media.Media;
import com.langesokker.views.FrontPageView;
import com.langesokker.views.MediaItemView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GUIController {

    private static GUIController instance;

    private final MediaController mediaController = MediaController.getInstance();

    private final JFrame frame;

    public GUIController(){
        frame = new JFrame("Lange sokker streaming");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static GUIController getInstance(){
        if (instance == null)  instance = new GUIController();
        return instance;
    }

    public void display(){
        frame.pack();
        frame.setVisible(true);
    }

    public void setupGUI(){
        frame.setMinimumSize(new Dimension(500, 300));
        frame.setPreferredSize(new Dimension(1920, 1000));

        frame.add(new FrontPageView(frame).getComponent());

    }


}

package com.langesokker.controllers;

import com.langesokker.views.BaseView;
import com.langesokker.views.FrontPageView;

import javax.swing.*;
import java.awt.*;

public class GUIController {

    private static GUIController instance;

    private final JFrame frame;
    private FrontPageView frontPage;

    public GUIController(){
        frame = new JFrame("Lange sokker streaming");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frontPage = new FrontPageView(frame);
    }

    public static GUIController getInstance(){
        if (instance == null)  instance = new GUIController();
        return instance;
    }

    /**
     * Synliggør framen og samler de diverse komponenter til at blive
     * */
    public void display(){
        frame.setTitle(this.generateTitle(frontPage));
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Laver en base-størrelse for GUI'ens frame
     */
    public void setupGUI(){
        frame.setMinimumSize(new Dimension(500, 300));
        frame.setPreferredSize(new Dimension(1920, 1000));

        frame.add(frontPage.getContainer());

    }

    public JFrame getFrame() {
        return frame;
    }

    public void setView(BaseView view){
        Container container = view.getContainer();
        frame.getContentPane().removeAll();
        frame.setContentPane(container);
        frame.revalidate();
        frame.repaint();
        frame.setTitle(generateTitle(view));

    }

    private String generateTitle(BaseView view){
        return "Lange sokker streaming - " + view.getViewName();
    }

    public void resetFrontPage(){
        this.frontPage = new FrontPageView(frame);
    }

    public FrontPageView getFrontPage() {
        return frontPage;
    }
}

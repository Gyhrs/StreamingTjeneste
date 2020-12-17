package com.langesokker.controllers;

import com.langesokker.views.BaseView;
import com.langesokker.views.FrontPageView;

import javax.swing.*;
import java.awt.*;

public class GUIController {

    private static GUIController instance;

    private final JFrame frame;
    private FrontPageView frontPage;

    /**
     * Konstrukt&oslash;r af GUIController
     */
    public GUIController(){
        frame = new JFrame("Lange sokker streaming");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frontPage = new FrontPageView(frame);
    }

    /**
     * Laver en singleton af GUIController hvis den ikke findes i forvejen
     * @return = GUIController singletong
     */
    public static GUIController getInstance(){
        if (instance == null)  instance = new GUIController();
        return instance;
    }

    /**
     * Synligg&oslash;r framen og samler de diverse komponenter til at blive
     * */
    public void display(){
        frame.setTitle(this.generateTitle(frontPage));
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Laver en base-st&oslash;rrelse for GUI'ens frame
     */
    public void setupGUI(){
        frame.setMinimumSize(new Dimension(500, 300));
        frame.setPreferredSize(new Dimension(1920, 1000));

        frame.add(frontPage.getContainer());

    }

    /**
     * @return hoved frameen
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Erstater den nuv&aelig;rende view med et nyt
     * @param view =  Det nye view som skal vises i framen
     */
    public void setView(BaseView view){
        Container container = view.getContainer();
        frame.getContentPane().removeAll();
        frame.setContentPane(container);
        frame.revalidate();
        frame.repaint();
        frame.setTitle(generateTitle(view));

    }

    /**
     * En hj&aelig;lpe metode til at tilf&oslash;je "Lange sokker streaming - " foran view navnet
     * @param view = BaseViewet der skal tages udgangspunkt
     * @return = String som kan bruges i frame titlen
     */
    private String generateTitle(BaseView view){
        return "Lange sokker streaming - " + view.getViewName();
    }

    /**
     * Erstatter det nuv&aelig;rende forside objekt med et nyt
     */
    public void resetFrontPage(){
        this.frontPage = new FrontPageView(frame);
    }

    /**
     * @return = Det nuv&aelig;rende forside objekt
     */
    public FrontPageView getFrontPage() {
        return frontPage;
    }
}

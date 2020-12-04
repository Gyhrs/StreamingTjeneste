package com.langesokker.views;

import com.langesokker.components.JText;
import com.langesokker.controllers.GUIController;
import com.langesokker.controllers.MediaController;
import com.langesokker.media.Media;
import com.langesokker.utils.ImageUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class FullMediaView extends BaseView {

    private Media media;
    private final MediaController mediaController = MediaController.getInstance();
    private final GUIController guiController = GUIController.getInstance();

    public FullMediaView (JFrame frame, Media media) {
        super(frame);
        this.media = media;
    }

    /**
     *
     */
    @Override
    public Container getContainer() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.LINE_AXIS));
        JPanel imagePanel = new JPanel();
        JPanel infoPanel = new JPanel();

        imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.Y_AXIS));
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        imagePanel.setAlignmentY(Component.TOP_ALIGNMENT);
        infoPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        imagePanel.add(new JLabel(new ImageIcon(mediaController.getMediaImage(media))));
        BufferedImage playIcon = ImageUtils.getImage("assets/play_icon_transparent.png");
        if(playIcon != null){
            playIcon = ImageUtils.resize(playIcon, 70, 70);
            imagePanel.add(new JLabel(new ImageIcon(playIcon)));
        }

        infoPanel.add(new JText("Title: " + media.getName(), 50, true));
        infoPanel.add(new JText("SomeDescription", 30));
        infoPanel.add(new JText("Genre: " + media.genresToString(), 20));
        infoPanel.add(new JText("Rating: " + media.getRating(), 20, true));

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> guiController.setView(guiController.getFrontPage().getContainer()));
        infoPanel.add(backButton);

        mainPanel.add(imagePanel);
        mainPanel.add(infoPanel);

        return mainPanel;
    }
}

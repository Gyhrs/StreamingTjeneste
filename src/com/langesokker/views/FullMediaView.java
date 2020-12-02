package com.langesokker.views;

import com.langesokker.JText;
import com.langesokker.controllers.GUIController;
import com.langesokker.controllers.MediaController;
import com.langesokker.media.Media;
import com.langesokker.utils.ImageUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        mainPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        Container imageContainer = new Container();
        Container infoContainer = new Container();

        imageContainer.setLayout(new BoxLayout(imageContainer, BoxLayout.PAGE_AXIS));
        infoContainer.setLayout(new BoxLayout(infoContainer, BoxLayout.Y_AXIS));
        imageContainer.add(new JLabel(new ImageIcon(mediaController.getMediaImage(media))));
        BufferedImage playIcon = ImageUtils.getImage("assets/play_icon_transparent.png");
        if(playIcon != null){
            playIcon = ImageUtils.resize(playIcon, 100, 100);
            imageContainer.add(new JLabel(new ImageIcon(playIcon)));
        }

        JText title = new JText("Title: " + media.getName(), 50, true);
        title.setAlignmentY(Component.TOP_ALIGNMENT);
        infoContainer.add(title);
        infoContainer.add(new JText("SomeDescription", 30));
        infoContainer.add(new JText("Genre: " + media.genresToString(), 20));
        infoContainer.add(new JText("Rating: " + media.getRating(), 20, true));
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiController.setView(guiController.getFrontPage().getContainer());
            }
        });
        infoContainer.add(backButton);
        mainPanel.add(imageContainer);
        mainPanel.add(infoContainer);

        return mainPanel;
    }
}

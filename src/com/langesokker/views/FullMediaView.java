package com.langesokker.views;

import com.langesokker.components.JText;
import com.langesokker.components.containers.NavBarContainer;
import com.langesokker.controllers.GUIController;
import com.langesokker.controllers.MediaController;
import com.langesokker.controllers.UserController;
import com.langesokker.media.Media;
import com.langesokker.models.User;
import com.langesokker.utils.Colors;
import com.langesokker.utils.ImageUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class FullMediaView extends BaseView {

    private Media media;
    private final MediaController mediaController = MediaController.getInstance();
    private final GUIController guiController = GUIController.getInstance();
    private final UserController userController = UserController.getInstance();


    public FullMediaView (JFrame frame, Media media) {
        super(frame);
        this.media = media;
    }

    /**
     *
     */
    @Override
    public Container getContainer() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.LINE_AXIS));
        mainPanel.setBackground(Colors.SECONDARY_DARK.getColor());
        JPanel imagePanel = new JPanel();
        imagePanel.setOpaque(false);
        JPanel infoPanel = new JPanel();
        infoPanel.setOpaque(false);

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

        infoPanel.add(new JText("Title: " + media.getName(), 50, true, Colors.WHITE.getColor()));
        infoPanel.add(new JText("SomeDescription", 30, Colors.WHITE.getColor()));
        infoPanel.add(new JText("Genre: " + media.genresToString(), 20, Colors.WHITE.getColor()));
        infoPanel.add(new JText("Rating: " + media.getRating(), 20, true, Colors.WHITE.getColor()));

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> guiController.setView(guiController.getFrontPage().getContainer()));
        infoPanel.add(backButton);

        JButton addToMyList = new JButton("Add to my list");
        addToMyList.addActionListener(e -> {
            userController.addMediaToUser(userController.getCurrentUser(), media);
        });
        infoPanel.add(addToMyList);

        JButton removeFromMyList = new JButton("Remove from my list");
        removeFromMyList.addActionListener(e -> {
            userController.removeMediaFromUser(userController.getCurrentUser(), media);
        });
        infoPanel.add(removeFromMyList);

        mainPanel.add(imagePanel);
        mainPanel.add(infoPanel);
        panel.add(new NavBarContainer(new Container()).getContainer(), BorderLayout.NORTH);
        panel.add(mainPanel, BorderLayout.CENTER);
        return panel;
    }
}

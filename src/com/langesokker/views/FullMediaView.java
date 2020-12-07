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
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
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
        infoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        imagePanel.add(new JLabel(new ImageIcon(mediaController.getMediaImage(media))));
        BufferedImage playIcon = ImageUtils.getImage("assets/play_icon_transparent.png");
        if(playIcon != null){
            playIcon = ImageUtils.resize(playIcon, 70, 70);
            imagePanel.add(new JLabel(new ImageIcon(playIcon)));
        }


        infoPanel.add(new JText("Title: " + media.getName(), 50, true, Colors.WHITE.getColor()));
        infoPanel.add(new JText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris sed risus in ligula porta sodales. Nulla facilisi. Mauris interdum purus dui, eu placerat sem posuere at. Aliquam non odio sed nunc condimentum interdum vitae mattis justo. Nullam a risus a sem molestie vulputate. Ut pulvinar augue vitae neque sodales, eget condimentum risus vulputate.", 30, Colors.WHITE.getColor()));
        infoPanel.add(new JText("Genre: " + media.genresToString(), 20, Colors.WHITE.getColor()));

        Container ratingContainer = new Container();
        ratingContainer.setLayout(new BoxLayout(ratingContainer, BoxLayout.LINE_AXIS));
        JText ratingText = new JText("Rating: " + media.getRating(), 20, true, Colors.WHITE.getColor());
        ratingText.setBorder(new EmptyBorder(0, 0, 0, 5));
        ratingContainer.add(ratingText);
        BufferedImage ratingIcon = ImageUtils.getImage("assets/star.png");
        if(ratingIcon != null){
            ratingIcon = ImageUtils.resize(ratingIcon, ratingText.getFontSize(), ratingText.getFontSize());
            ratingContainer.add(new JLabel(new ImageIcon(ratingIcon)));
        }
        infoPanel.add(ratingContainer);

        JButton backButton = createSimpleButton("Back");
        backButton.addActionListener(e -> guiController.setView(guiController.getFrontPage().getContainer()));
        infoPanel.add(backButton);

        JButton addToMyList = createSimpleButton("Add to my list");
        addToMyList.addActionListener(e -> {
            userController.addMediaToUser(userController.getCurrentUser(), media);
        });
        infoPanel.add(addToMyList);

        JButton removeFromMyList = createSimpleButton("Remove from my list");
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

    private JButton createSimpleButton(String text) {
        JButton button = new JButton(text);
        button.setForeground(Color.BLACK);
        button.setBackground(Color.WHITE);
        Border line = new LineBorder(Color.BLACK);
        Border margin = new EmptyBorder(10, 15, 10, 15);
        Border compound = new CompoundBorder(line, margin);
        button.setBorder(compound);
        return button;
    }
}

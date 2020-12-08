package com.langesokker.views;

import com.langesokker.components.JText;
import com.langesokker.components.PlayButton;
import com.langesokker.components.RatingContainer;
import com.langesokker.components.containers.EpisodeSelectorContainer;
import com.langesokker.components.containers.NavBarContainer;
import com.langesokker.controllers.GUIController;
import com.langesokker.controllers.MediaController;
import com.langesokker.controllers.UserController;
import com.langesokker.media.Media;
import com.langesokker.media.Seasonable;
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

    private final Media media;
    private final MediaController mediaController = MediaController.getInstance();
    private final GUIController guiController = GUIController.getInstance();
    private final UserController userController = UserController.getInstance();


    public FullMediaView(JFrame frame, Media media) {
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

        imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.Y_AXIS));
        imagePanel.setAlignmentY(JPanel.TOP_ALIGNMENT);
        imagePanel.setSize(200, 400);

        JLabel image = new JLabel(new ImageIcon(mediaController.getMediaImage(media)));
        imagePanel.add(image);

        /* Add a play button */
        JButton playButton = new PlayButton();
        imagePanel.add(playButton);

        /* Add season/episode select if possible */
        if (media instanceof Seasonable) {
            Seasonable seasonable = (Seasonable) media;
            imagePanel.add(new EpisodeSelectorContainer(seasonable));
        }

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));
        infoPanel.setOpaque(false);
        infoPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        JPanel innerPanel = getInnerInfoPanel();
        infoPanel.add(innerPanel);

        mainPanel.add(imagePanel, Component.TOP_ALIGNMENT);
        mainPanel.add(infoPanel);
        panel.add(new NavBarContainer(new Container()), BorderLayout.NORTH);
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

    private JPanel getInnerInfoPanel(){
        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
        innerPanel.setOpaque(false);

        innerPanel.setSize(new Dimension(100, 200));

        JText title = new JText(
                String.format("<html><div style=\"width:%dpx; text-align:left; text-transform: uppercase;\">%s</div></html>", 500, media.getName()),
                72,
                true,
                Color.WHITE);

        title.setBorder(BorderFactory.createLineBorder(Color.black));
        innerPanel.add(title);

        /*Container ratingContainer = new RatingContainer(media, FlowLayout.LEFT, true);
        innerPanel.add(ratingContainer);*/

        innerPanel.add(new JText("Release date: " + media.getReleaseDate(), 20, true, Colors.WHITE.getColor()));
        innerPanel.add(new JText(media.genresToString(), 20, Colors.WHITE.getColor()));
        innerPanel.setAlignmentX(SwingConstants.LEFT);

        Box buttonBox = Box.createHorizontalBox();

        JButton backButton = createSimpleButton("Back");
        backButton.addActionListener(e -> guiController.setView(guiController.getFrontPage().getContainer()));
        buttonBox.add(backButton);
        buttonBox.add(Box.createRigidArea(new Dimension(30,0)));

        buttonBox.add(createAddToListButton());
        innerPanel.add(buttonBox);
        return innerPanel;
    }


    private JButton createAddToListButton() {
        JButton myListButton = createSimpleButton("Add to my list");
        User currentUser = userController.getCurrentUser();
        if (!currentUser.isInList(media)) {
            myListButton.setText("Add to my list");
        } else {
            myListButton.setText("Remove from my list");
        }
        myListButton.addActionListener(e -> {
            if (currentUser.isInList(media)) {
                userController.removeMediaFromUser(currentUser, media);
                myListButton.setText("Add to my list");
            } else {
                userController.addMediaToUser(currentUser, media);
                myListButton.setText("Remove from my list");
            }
        });
        return myListButton;
    }
}
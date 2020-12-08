package com.langesokker.views;

import com.langesokker.components.JText;
import com.langesokker.components.PlayButton;
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
        /*imagePanel.setBackground(Color.GREEN);*/
        JPanel infoPanel = new JPanel();
        infoPanel.setOpaque(false);
        /*infoPanel.setBackground(Color.BLUE);*/
        imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.Y_AXIS));
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        /*imagePanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        imagePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        infoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);*/
        infoPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        infoPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        JLabel image = new JLabel(new ImageIcon(mediaController.getMediaImage(media)));
        image.setVerticalAlignment(SwingConstants.CENTER);
        imagePanel.add(image);

        /* Add a play button */
        JButton playButton = new PlayButton();
        imagePanel.add(playButton);

        /* Add season/episode select if possible */
        if (media instanceof Seasonable) {
            Seasonable seasonable = (Seasonable) media;
            imagePanel.add(new EpisodeSelectorContainer(seasonable));
        }

        Box innerBox = Box.createVerticalBox();
        innerBox.setOpaque(false);
        //innerPanel.setBackground(Color.YELLOW);
        innerBox.setSize(new Dimension(10, 500));
        innerBox.setBorder(BorderFactory.createLineBorder(Color.black));
        //innerBox.setAlignmentX(SwingConstants.LEFT);

        /*JText title = new JText(
                String.format("<html><div style=\"width:%dpx; text-align:left; text-transform: uppercase;\">%s</div></html>", 500, media.getName()),
                72,
                true,
                Color.WHITE);*/
        JText title = new JText(media.getName(),
                72,
                true,
                Color.WHITE);

        title.setBorder(BorderFactory.createLineBorder(Color.black));
        innerBox.add(title);
        Box infoBar = Box.createHorizontalBox();
        infoBar.setOpaque(false);

        JPanel ratingContainer = new JPanel();
        ratingContainer.setOpaque(false);
        ratingContainer.setLayout(new BoxLayout(ratingContainer, BoxLayout.X_AXIS));
        JText ratingText = new JText("Rating: " + media.getRating(), 20, true, Colors.WHITE.getColor());
        ratingText.setBorder(new EmptyBorder(0, 0, 0, 5));
        ratingContainer.add(ratingText);
        BufferedImage ratingIcon = ImageUtils.getImage("assets/star.png");
        if (ratingIcon != null) {
            ratingIcon = ImageUtils.resize(ratingIcon, ratingText.getFontSize(), ratingText.getFontSize());
            ratingContainer.add(new JLabel(new ImageIcon(ratingIcon)));
        }
        infoBar.add(ratingContainer);
        infoBar.add(Box.createRigidArea(new Dimension(30,0)));

        infoBar.add(new JText("Release date: " + media.getReleaseDate(), 20, true, Colors.WHITE.getColor()));
        infoBar.add(Box.createRigidArea(new Dimension(30,0)));
        infoBar.add(new JText(media.genresToString(), 20, Colors.WHITE.getColor()));
        infoBar.setAlignmentX(SwingConstants.LEFT);

        innerBox.add(infoBar);

        Box buttonBox = Box.createHorizontalBox();

        JButton backButton = createSimpleButton("Back");
        backButton.addActionListener(e -> guiController.setView(guiController.getFrontPage().getContainer()));
        buttonBox.add(backButton);
        buttonBox.add(Box.createRigidArea(new Dimension(30,0)));


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
        buttonBox.add(myListButton);
        innerBox.add(buttonBox);
        infoPanel.add(innerBox);

        /*infoPanel.add(new JText("Title: " + media.getName(), 50, true, Colors.WHITE.getColor()));
        infoPanel.add(new JText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris sed risus in ligula porta sodales. Nulla facilisi. Mauris interdum purus dui, eu placerat sem posuere at. Aliquam non odio sed nunc condimentum interdum vitae mattis justo. Nullam a risus a sem molestie vulputate. Ut pulvinar augue vitae neque sodales, eget condimentum risus vulputate.", 30, Colors.WHITE.getColor()));
        infoPanel.add(new JText("Genre: " + media.genresToString(), 20, Colors.WHITE.getColor()));*/

        mainPanel.add(imagePanel);
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
}
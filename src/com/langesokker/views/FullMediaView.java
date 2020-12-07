package com.langesokker.views;

import com.langesokker.components.JText;
import com.langesokker.components.containers.NavBarContainer;
import com.langesokker.controllers.GUIController;
import com.langesokker.controllers.MediaController;
import com.langesokker.controllers.UserController;
import com.langesokker.media.Media;
import com.langesokker.media.Series;
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
    boolean isPlaying = false;


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
        JPanel infoPanel = new JPanel();
        infoPanel.setOpaque(false);

        imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.Y_AXIS));
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        imagePanel.setAlignmentY(Component.TOP_ALIGNMENT);
        infoPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        infoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        imagePanel.add(new JLabel(new ImageIcon(mediaController.getMediaImage(media))));
        JButton playButton = new JButton();
        BufferedImage playIcon = ImageUtils.getImage("assets/play_icon_transparent.png");
        BufferedImage pauseIcon = ImageUtils.getImage("assets/pauseicon.png");

        if (playIcon != null) {
            playIcon = ImageUtils.resize(playIcon, 70, 70);
            playButton.setIcon(new ImageIcon(playIcon));
        }
        if (pauseIcon != null) {
            pauseIcon = ImageUtils.resize(pauseIcon, 70, 70);
        }


        BufferedImage finalPlayIcon = playIcon;
        BufferedImage finalPauseIcon = pauseIcon;

        playButton.addActionListener(e -> {
            if (isPlaying) {
                if (finalPlayIcon != null) {
                    playButton.setIcon(new ImageIcon(finalPlayIcon));

                }
                isPlaying = false;
            } else {
                isPlaying = true;
                if (finalPauseIcon != null) {
                    playButton.setIcon(new ImageIcon(finalPauseIcon));

                }
            }
            playButton.repaint();
        });
        imagePanel.add(playButton);

        infoPanel.add(new JText("Title: " + media.getName(), 50, true, Colors.WHITE.getColor()));
        infoPanel.add(new JText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris sed risus in ligula porta sodales. Nulla facilisi. Mauris interdum purus dui, eu placerat sem posuere at. Aliquam non odio sed nunc condimentum interdum vitae mattis justo. Nullam a risus a sem molestie vulputate. Ut pulvinar augue vitae neque sodales, eget condimentum risus vulputate.", 30, Colors.WHITE.getColor()));
        infoPanel.add(new JText("Genre: " + media.genresToString(), 20, Colors.WHITE.getColor()));

        Container ratingContainer = new Container();
        ratingContainer.setLayout(new BoxLayout(ratingContainer, BoxLayout.LINE_AXIS));
        JText ratingText = new JText("Rating: " + media.getRating(), 20, true, Colors.WHITE.getColor());
        ratingText.setBorder(new EmptyBorder(0, 0, 0, 5));
        ratingContainer.add(ratingText);
        BufferedImage ratingIcon = ImageUtils.getImage("assets/star.png");
        if (ratingIcon != null) {
            ratingIcon = ImageUtils.resize(ratingIcon, ratingText.getFontSize(), ratingText.getFontSize());
            ratingContainer.add(new JLabel(new ImageIcon(ratingIcon)));
        }
        infoPanel.add(ratingContainer);

        JButton backButton = createSimpleButton("Back");
        backButton.addActionListener(e -> guiController.setView(guiController.getFrontPage().getContainer()));
        infoPanel.add(backButton);

        JButton myListButton = createSimpleButton("Add to my list");
        if (!media.isInList()) {
            myListButton.setText("Add to my list");
        } else {
            myListButton.setText("Remove from my list");
        }
        myListButton.addActionListener(e -> {
            if (media.isInList()) {
                userController.removeMediaFromUser(userController.getCurrentUser(), media);
                myListButton.setText("Add to my list");
                media.setInList(false);
            } else if (!media.isInList()) {
                userController.addMediaToUser(userController.getCurrentUser(), media);
                myListButton.setText("Remove from my list");
                media.setInList(true);
            }
        });
        infoPanel.add(myListButton);

        if (media instanceof Series) {
            Series series = (Series) media;
            // TODO: Make seasonable interface

            Integer[] seasons = series.getSeasons().keySet().toArray(new Integer[]{});
            JComboBox<Integer> seasonsBox = new JComboBox<>(seasons);
            infoPanel.add(seasonsBox);

            Integer[] episodes = series.getEpisodesInSeason(seasonsBox.getSelectedItem() != null? (int) seasonsBox.getSelectedItem() : 1);
            JComboBox<Integer> episodesBox = new JComboBox<>(episodes);
            infoPanel.add(episodesBox);

            seasonsBox.addItemListener(e -> {
                int season = (int) e.getItem();
                episodesBox.removeAllItems();
                Integer[] episodesInSeason = series.getEpisodesInSeason(season);
                episodesBox.setModel(new DefaultComboBoxModel<>(episodesInSeason));
            });
        }

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
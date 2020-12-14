package com.langesokker.views;

import com.langesokker.components.JText;
import com.langesokker.components.PlayButton;
import com.langesokker.components.containers.EpisodeSelectorContainer;
import com.langesokker.components.containers.JMultiLine;
import com.langesokker.components.containers.NavBarContainer;
import com.langesokker.components.containers.RatingContainer;
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

public class FullMediaView extends BaseView {

    private final Media media;
    private final MediaController mediaController = MediaController.getInstance();
    private final GUIController guiController = GUIController.getInstance();
    private final UserController userController = UserController.getInstance();
    private final BaseView previousView;

    /**
     * FullMediaView er siden som viser alt infoet om et givent medie.
     * @param frame = Den nuværrende frame
     * @param media = Mediet som skal vises
     * @param previousView = Siden brugeren kom fra. (Nullable)
     */
    public FullMediaView(JFrame frame, Media media, BaseView previousView) {
        super(frame);
        this.media = media;
        this.previousView = previousView;
    }

    /**
     * Returnere title navnet
     * @return View name
     */
    @Override
    public String getViewName() {
        return media.getName();
    }

    /**
     *
     * @return Containeren til siden
     */
    @Override
    public Container getContainer() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;
        c.weightx = 0;
        c.weighty = 0;
        mainPanel.setBackground(Colors.SECONDARY_DARK.getColor());

        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;
        JPanel imagePanel = createImagePanel();
        imagePanel.setSize(new Dimension(300, (int) imagePanel.getPreferredSize().getHeight()));
        mainPanel.add(imagePanel, c);

        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_END;

        JPanel infoPanel = createInfoPanel();
        infoPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        mainPanel.add(infoPanel, c);
        panel.add(new NavBarContainer(new Container()), BorderLayout.NORTH);
        panel.add(mainPanel, BorderLayout.CENTER);
        return panel;
    }

    private JButton createSimpleButton(String text) {
        JButton button = new JButton(text);
        button.setOpaque(true);
        button.setForeground(Color.BLACK);
        button.setBackground(Color.WHITE);
        Border line = new LineBorder(Color.BLACK);
        Border margin = new EmptyBorder(10, 15, 10, 15);
        Border compound = new CompoundBorder(line, margin);
        button.setBorder(compound);
        return button;
    }

    private JPanel createImagePanel() {
        JPanel imagePanel = new JPanel();
        imagePanel.setOpaque(false);

        imagePanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        JLabel image = new JLabel(new ImageIcon(ImageUtils.resize(mediaController.getMediaImage(media), 200, 298)));
        imagePanel.add(image, c);

        c.gridy = 1;
        c.insets = new Insets(20, 20, 20, 20);
        /* Add a play button */
        JButton playButton = new PlayButton();
        imagePanel.add(playButton, c);

        c.gridy = 2;
        /* Add season/episode select if possible */
        if (media instanceof Seasonable) {
            Seasonable seasonable = (Seasonable) media;
            imagePanel.add(new EpisodeSelectorContainer(seasonable), c);
        }
        return imagePanel;
    }

    private JPanel createInfoPanel() {
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridBagLayout());
        infoPanel.setOpaque(false);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = GridBagConstraints.REMAINDER;
        JText title = new JText(
                String.format("<html><div style=\"width:%dpx; text-align:left; text-transform: uppercase;\">%s</div></html>", 1000, media.getName()),
                72,
                true,
                Color.WHITE);

        title.setBorder(BorderFactory.createLineBorder(Color.black));
        infoPanel.add(title, c);

        c.gridy = 1;
        c.gridx = 1;
        c.gridwidth = 1;
        JComponent ratingContainer = new RatingContainer(media, false);
        infoPanel.add(ratingContainer, c);

        c.gridy = 1;
        c.gridx = 2;
        c.gridwidth = 1;
        infoPanel.add(new JText("Release date: " + media.getReleaseDate(), 20, false, Colors.WHITE.getColor()), c);
        c.gridy = 1;
        c.gridx = 3;
        c.gridwidth = GridBagConstraints.REMAINDER;
        infoPanel.add(new JText(media.genresToString(), 20, Colors.WHITE.getColor()), c);
        c.gridwidth = 1;
        infoPanel.setAlignmentX(SwingConstants.LEFT);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = GridBagConstraints.REMAINDER;
        JMultiLine description = new JMultiLine("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras elementum nisl venenatis diam tincidunt, in fermentum ligula lobortis. Praesent malesuada enim in velit eleifend, eu eleifend dui maximus. Sed vel suscipit est, sed lobortis enim. Nam lobortis tellus eget pellentesque consequat. Nunc massa risus, molestie sed nisl a, molestie dictum massa. Phasellus nibh nisl, tempor in arcu at, semper blandit nisl. Aenean egestas vitae nunc eu eleifend. Morbi ut odio mauris.");
        infoPanel.add(description, c);
        c.gridwidth = 1;

        Box buttonBox = Box.createHorizontalBox();

        JButton backButton = createSimpleButton("Back");
        BaseView backView = previousView == null ? guiController.getFrontPage() : previousView;
        backButton.addActionListener(e -> guiController.setView(backView));
        buttonBox.add(backButton);
        buttonBox.add(createAddToListButton());
        c.gridy = 3;
        c.gridwidth = GridBagConstraints.REMAINDER;
        infoPanel.add(buttonBox, c);
        c.gridwidth = 1;
        return infoPanel;
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
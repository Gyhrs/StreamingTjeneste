package com.langesokker.components.containers;

import com.langesokker.components.JText;
import com.langesokker.components.TransparentJPanel;
import com.langesokker.controllers.GUIController;
import com.langesokker.controllers.MediaController;
import com.langesokker.media.Media;
import com.langesokker.utils.Colors;
import com.langesokker.views.BaseView;
import com.langesokker.views.FullMediaView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class MediaItemContainer extends JPanel{

    private final Media media;
    private final String name;
    private final BufferedImage image;
    private final GUIController guiController = GUIController.getInstance();
    private final BaseView currentView;

    public MediaItemContainer(Media media, BaseView currentView){
        this.media = media;
        this.name = media.getName();
        this.image = MediaController.getInstance().getMediaImage(media);
        this.currentView = currentView;

        this.setOpaque(false);
        this.setLayout(new OverlayLayout(this));
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.setPreferredSize(new Dimension(140,224));

        this.add(createOverlayPanel());
        this.add(createBackgroundContainer());
    }

    public MediaItemContainer(Media media){
        this(media, null);
    }

    private Container createBackgroundContainer() {
        Container backgroundContainer = new Container();
        backgroundContainer.setLayout(new BorderLayout());
        backgroundContainer.setPreferredSize(new Dimension(140,209));
        backgroundContainer.add(new JLabel(new ImageIcon(image)), BorderLayout.CENTER);
        return backgroundContainer;
    }

    private JPanel createOverlayPanel() {
        JPanel overlayPanel = new TransparentJPanel(.5f, new Color(0,0,0));
        overlayPanel.setLayout(new BorderLayout());
        overlayPanel.setPreferredSize(new Dimension(140,229));

        JText text = new JText(String.format("<html><div style=\"width:%dpx; text-align:center;\">%s</div></html>", 100, name),20,true, Color.WHITE);
        text.setHorizontalAlignment(JLabel.CENTER);
        text.setVerticalAlignment(JLabel.CENTER);
        text.setOpaque(false);
        overlayPanel.add(text, BorderLayout.CENTER);
        RatingContainer ratingContainer = new RatingContainer(media);
        ratingContainer.setOpaque(false);
        ratingContainer.setBackground(Colors.SECONDARY_DARK.getColor());
        overlayPanel.add(ratingContainer, BorderLayout.SOUTH);
        overlayPanel.setOpaque(false);
        overlayPanel.setVisible(false);

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getButton() != MouseEvent.BUTTON1) return;
                guiController.setView(new FullMediaView(guiController.getFrame(), media, currentView));
            }

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                overlayPanel.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                overlayPanel.setVisible(false);
            }
        });
        return overlayPanel;
    }
}

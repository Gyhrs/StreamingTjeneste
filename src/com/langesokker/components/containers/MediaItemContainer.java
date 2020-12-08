package com.langesokker.components.containers;

import com.langesokker.components.JText;
import com.langesokker.components.TransparentJPanel;
import com.langesokker.controllers.GUIController;
import com.langesokker.controllers.MediaController;
import com.langesokker.media.Media;
import com.langesokker.views.FullMediaView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class MediaItemContainer extends JPanel{

    Media media;
    String name;
    BufferedImage image;
    private final GUIController guiController = GUIController.getInstance();

    public MediaItemContainer(Media media){
        this.media = media;
        this.name = media.getName();
        this.image = MediaController.getInstance().getMediaImage(media);

        this.setOpaque(false);
        this.setLayout(new OverlayLayout(this));
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.setPreferredSize(new Dimension(140,224));

        this.add(createOverlayPanel());
        this.add(createBackgroundContainer());
    }

    private Container createBackgroundContainer() {
        Container backgroundContainer = new Container();
        backgroundContainer.setLayout(new BorderLayout());
        backgroundContainer.setPreferredSize(new Dimension(140,224));
        backgroundContainer.add(new JLabel(new ImageIcon(image)), BorderLayout.CENTER);
        return backgroundContainer;
    }

    private JPanel createOverlayPanel() {
        JPanel overlayPanel = new TransparentJPanel(1f, new Color(0,0,0, 10));
        overlayPanel.setLayout(new BorderLayout());
        overlayPanel.setPreferredSize(new Dimension(140,224));

        JText text = new JText(String.format("<html><div style=\"width:%dpx; text-align:center;\">%s</div></html>", 100, name),20,true, Color.WHITE);
        text.setHorizontalAlignment(JLabel.CENTER);
        text.setVerticalAlignment(JLabel.CENTER);

        overlayPanel.add(text, BorderLayout.CENTER);
        overlayPanel.add(new RatingContainer(media), BorderLayout.SOUTH);
        overlayPanel.setVisible(false);

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getButton() != MouseEvent.BUTTON1) return;
                guiController.setView(new FullMediaView(guiController.getFrame(), media).getContainer());
            }

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                overlayPanel.setVisible(true);
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                overlayPanel.setVisible(false);
                repaint();
            }
        });
        return overlayPanel;
    }
}

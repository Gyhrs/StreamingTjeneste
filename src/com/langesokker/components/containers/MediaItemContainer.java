package com.langesokker.components.containers;

import com.langesokker.components.JText;
import com.langesokker.components.TransparentJPanel;
import com.langesokker.controllers.GUIController;
import com.langesokker.controllers.MediaController;
import com.langesokker.media.Media;
import com.langesokker.utils.Colors;
import com.langesokker.utils.ImageUtils;
import com.langesokker.views.FullMediaView;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class MediaItemContainer {

    Media media;
    String name;
    BufferedImage image;
    private final GUIController guiController = GUIController.getInstance();

    public MediaItemContainer(Media media){
        this.media = media;
        this.name = media.getName();
        this.image = MediaController.getInstance().getMediaImage(media);
    }

    public Container getContainer(){
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new OverlayLayout(panel));
        panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.setPreferredSize(new Dimension(140,224));

        Container backgroundContainer = new Container();
        backgroundContainer.setLayout(new BorderLayout());
        backgroundContainer.setPreferredSize(new Dimension(140,224));
        backgroundContainer.add(new JLabel(new ImageIcon(image)), BorderLayout.CENTER);

        JPanel overlayPanel = new TransparentJPanel(1f, new Color(0,0,0, 10));
        overlayPanel.setLayout(new BorderLayout());
        overlayPanel.setPreferredSize(new Dimension(140,224));
        JText text = new JText(String.format("<html><div style=\"width:%dpx; text-align:center;\">%s</div></html>", 100, name),20,true, Color.WHITE);
        text.setHorizontalAlignment(JLabel.CENTER);
        text.setVerticalAlignment(JLabel.CENTER);
        overlayPanel.add(text, BorderLayout.CENTER);
        Container ratingContainer = new Container();
        ratingContainer.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JText ratingText = new JText(media.getRating() + "", 15, true, Colors.WHITE.getColor());
        ratingText.setBorder(new EmptyBorder(0, 0, 0, 2));
        ratingContainer.add(ratingText);
        BufferedImage ratingIcon = ImageUtils.getImage("assets/star.png");
        if(ratingIcon != null){
            ratingIcon = ImageUtils.resize(ratingIcon, ratingText.getFontSize(), ratingText.getFontSize());
            ratingContainer.add(new JLabel(new ImageIcon(ratingIcon)));
        }
        overlayPanel.add(ratingContainer, BorderLayout.SOUTH);
        overlayPanel.setVisible(false);

        panel.addMouseListener(new MouseListener() {
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
                panel.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                overlayPanel.setVisible(false);
                panel.repaint();
            }
        });
        panel.add(overlayPanel);
        panel.add(backgroundContainer);

        return panel;

    }
}

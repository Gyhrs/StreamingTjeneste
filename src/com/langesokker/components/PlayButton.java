package com.langesokker.components;

import com.langesokker.utils.ImageUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayButton extends JButton{

    private final BufferedImage playIcon;
    private final BufferedImage pauseIcon;

    boolean isPlaying = false;

    /**
     * Konstruktør til PlayButton
     * Dette er en hjælpe klasse for at gøre koden mere overskuelig
     */
    public PlayButton(){
        this.setBorder(new EmptyBorder(0,0,0,0));
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.setOpaque(false);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.playIcon = ImageUtils.resize(ImageUtils.getImage("assets/play_icon_transparent.png"), 90, 90);
        this.pauseIcon = ImageUtils.resize(ImageUtils.getImage("assets/pauseicon.png"), 90, 90);

        this.setVerticalAlignment(SwingConstants.CENTER);

        this.setIcon(new ImageIcon(playIcon));

        this.addActionListener(e -> {
            if (isPlaying) {
                this.setIcon(new ImageIcon(playIcon));
                isPlaying = false;
            } else {
                isPlaying = true;
                this.setIcon(new ImageIcon(pauseIcon));
            }
            this.repaint();
        });
    }

}

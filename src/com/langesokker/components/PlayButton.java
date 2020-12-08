package com.langesokker.components;

import com.langesokker.utils.ImageUtils;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class PlayButton extends JButton{

    private final BufferedImage playIcon;
    private final BufferedImage pauseIcon;

    boolean isPlaying = false;

    public PlayButton(){
        this.playIcon = ImageUtils.resize(ImageUtils.getImage("assets/play_icon_transparent.png"), 70, 70);
        this.pauseIcon = ImageUtils.resize(ImageUtils.getImage("assets/pauseicon.png"), 70, 70);

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

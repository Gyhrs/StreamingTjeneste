package com.langesokker.components;

import com.langesokker.media.Media;
import com.langesokker.utils.Colors;
import com.langesokker.utils.ImageUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;

public class RatingContainer extends Container {

    public RatingContainer(Media media) {
        this(media, FlowLayout.RIGHT, false);
    }

    public RatingContainer(Media media, int alignment, boolean includeText) {

        this.setLayout(new FlowLayout(alignment));

        JText ratingText = new JText((includeText ? "Rating: " : "") + media.getRating(), 15, true, Colors.WHITE.getColor());
        ratingText.setBorder(new EmptyBorder(0, 0, 0, 2));
        this.add(ratingText);

        BufferedImage ratingIcon = ImageUtils.getImage("assets/star.png");
        if(ratingIcon != null){
            ratingIcon = ImageUtils.resize(ratingIcon, ratingText.getFontSize(), ratingText.getFontSize());
            this.add(new JLabel(new ImageIcon(ratingIcon)));
        }
    }
}

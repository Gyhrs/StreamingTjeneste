package com.langesokker.components.containers;

import com.langesokker.components.JText;
import com.langesokker.media.Media;
import com.langesokker.utils.Colors;
import com.langesokker.utils.ImageUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class RatingContainer extends JComponent {

    public RatingContainer(Media media) {
        this(media, true);
    }

    public RatingContainer(Media media, boolean frontPage) {
        this.setLayout(frontPage ? new FlowLayout(FlowLayout.RIGHT, 0, 20) : new BoxLayout(this, BoxLayout.LINE_AXIS));

        this.setMaximumSize(new Dimension(this.getWidth(), this.getHeight()));
        this.setOpaque(frontPage);
        JText ratingText = new JText(((!frontPage ? "Rating: " : "") + media.getRating()), 15, true, Colors.WHITE.getColor());
        this.add(ratingText);

        BufferedImage ratingIcon = ImageUtils.getImage("assets/star.png");
        if(ratingIcon != null){
            ratingIcon = ImageUtils.resize(ratingIcon, ratingText.getFontSize(), ratingText.getFontSize());
            this.add(new JLabel(new ImageIcon(ratingIcon)));
        }
    }
}

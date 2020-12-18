package com.langesokker.components.containers;

import com.langesokker.components.JText;
import com.langesokker.exceptions.ImageNotFoundException;
import com.langesokker.media.Media;
import com.langesokker.utils.Colors;
import com.langesokker.utils.ImageUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class RatingContainer extends JComponent {

    /**
     * Overloaded kontrukt&oslash;r til RatingContainer hvor man g&aring;r ud fra at det er p&aring; forsiden den bliver k&oslash;rt
     * @param media = Mediet som der skal tages udgangspunkt i
     */
    public RatingContainer(Media media) {
        this(media, true);
    }

    /**
     * Konstrukt&oslash;r til RatingContainer
     * @param media = Mediet som der skal tages udgangspunkt i
     * @param frontPage = Er det p&aring; forsiden det bliver displayed
     */
    public RatingContainer(Media media, boolean frontPage) {
        this.setLayout(frontPage ? new FlowLayout(FlowLayout.RIGHT, 0, 20) : new BoxLayout(this, BoxLayout.LINE_AXIS));

        this.setMaximumSize(new Dimension(this.getWidth(), this.getHeight()));
        this.setOpaque(frontPage);
        JText ratingText = new JText(((!frontPage ? "Rating: " : "") + media.getRating()), 15, true, Colors.WHITE.getColor());
        this.add(ratingText);

        //Only show the star if loaded correctly otherwise the star is not necessary for the user experience
        BufferedImage ratingIcon = null;
        try{
            ratingIcon = ImageUtils.getImage("assets/star.png");
        }catch(ImageNotFoundException e){
            System.out.println("Could not load rating star at " + e.getPath()); //Not necessary for the user to see
        }
        if(ratingIcon != null){
            ratingIcon = ImageUtils.resize(ratingIcon, ratingText.getFontSize(), ratingText.getFontSize());
            this.add(new JLabel(new ImageIcon(ratingIcon)));
        }
    }
}

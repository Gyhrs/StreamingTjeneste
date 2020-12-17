package com.langesokker.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * En hj&aelig;lpeklasse til loading og resizing a billeder
 */
public class ImageUtils {

    /**
     * Loader et billede
     * @param path Den lokale sti fra packagen
     * @return BufferedImage den loaded billede
     */
    public static BufferedImage getImage(String path){
        String basePath = "com/langesokker/";
        InputStream is = ImageUtils.class.getClassLoader().getResourceAsStream(basePath + path);
        if(is == null) return null;
        try {
            return ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Tilpasser st&oslash;rrelsen p&aring; billedet
     * @param orgImage Den orginal billede
     * @param width Den nye st&oslash;rrelse width
     * @param height Den nye st&oslash;rrelse h&oslash;jde
     * @return Det nye billede
     */
    public static BufferedImage resize(BufferedImage orgImage, int width, int height){
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();
        graphics.drawImage(orgImage, 0, 0, width, height, null);
        return image;
    }
}

package com.langesokker.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class ImageUtils {

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
    public static BufferedImage resize(BufferedImage orgImage, int width, int height){
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();
        graphics.drawImage(orgImage, 0, 0, width, height, null);
        return image;
    }
}

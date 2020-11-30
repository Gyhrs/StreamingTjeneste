package com.langesokker.views;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MediaView {

    String name;
    BufferedImage image;

    public MediaView(String name, BufferedImage image){
        this.name = name;
        this.image = image;
    }

    public Component getComponent(){
        Container container = new Container();
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
        container.setMaximumSize(new Dimension(50,50));

        container.add(new JLabel(new ImageIcon(image)));
        container.add(new JLabel(name));

        return container;
    }
}

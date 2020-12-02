package com.langesokker.views;

import com.langesokker.JText;
import com.langesokker.controllers.GUIController;
import com.langesokker.controllers.MediaController;
import com.langesokker.media.Media;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class MediaItemView extends BaseView{

    Media media;
    String name;
    BufferedImage image;
    private final GUIController guiController = GUIController.getInstance();

    public MediaItemView(JFrame frame, Media media){
        super(frame);
        this.media = media;
        this.name = media.getName();
        this.image = MediaController.getInstance().getMediaImage(media);
    }

    @Override
    public Container getContainer(){
        Container container = new Container();
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
        container.setMaximumSize(new Dimension(50,50));
        container.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                guiController.setView(new FullMediaView(frame, media).getContainer());
            }

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });

        container.add(new JLabel(new ImageIcon(image)));
        container.add(new JText(name));

        return container;
    }
}

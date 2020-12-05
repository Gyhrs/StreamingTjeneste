package com.langesokker.components.containers;

import com.langesokker.components.JText;
import com.langesokker.controllers.GUIController;
import com.langesokker.controllers.MediaController;
import com.langesokker.media.Media;
import com.langesokker.views.FullMediaView;

import javax.swing.*;
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
        Container container = new Container();
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
        container.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        container.setMaximumSize(new Dimension(50,50));
        container.addMouseListener(new MouseListener() {
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
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });

        container.add(new JLabel(new ImageIcon(image)));
        container.add(new JText(name, Color.WHITE));
        return container;

    }
}

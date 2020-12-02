package com.langesokker.views;

import com.langesokker.media.Media;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Flow;

public class FullMediaView extends BaseView {

    private Media media;

    public FullMediaView (JFrame frame, Media media) {
        super(frame);
        this.media = media;
    }

    @Override
    public Component getComponent() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        Container imageContainer = new Container();
        Container infoContainer = new Container();
        imageContainer.setLayout(new BoxLayout(imageContainer, BoxLayout.PAGE_AXIS));
        infoContainer.setLayout(new BoxLayout(infoContainer, BoxLayout.PAGE_AXIS));
        return mainPanel;
    }
}

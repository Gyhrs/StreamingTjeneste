package com.langesokker.views;

import com.langesokker.media.Media;

import javax.swing.*;

public class FullMediaView extends BaseView {

    private Media media;

    public FullMediaView (JFrame frame, Media media) {
        super(frame);
        this.media = media;
    }
}

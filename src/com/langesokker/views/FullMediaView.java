package com.langesokker.views;

import com.langesokker.media.Media;

import javax.swing.*;
import java.awt.*;

public class FullMediaView extends BaseView {

    private Media media;

    public FullMediaView (JFrame frame, Media media) {
        super(frame);
        this.media = media;
    }

    @Override
    public Component getComponent() {
        return null;
    }
}

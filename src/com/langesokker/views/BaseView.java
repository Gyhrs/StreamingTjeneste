package com.langesokker.views;

import javax.swing.*;

public abstract class BaseView {

    protected JFrame frame;

    public BaseView(JFrame frame) {
        this.frame = frame;
    }

    public JFrame getFrame() {
        return frame;
    }
}

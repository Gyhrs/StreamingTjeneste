package com.langesokker.views;

import javax.swing.*;
import java.awt.*;

public abstract class BaseView {

    protected JFrame frame;

    public BaseView(JFrame frame) {
        this.frame = frame;
    }

    public JFrame getFrame() {
        return frame;
    }

    public abstract Container getContainer();
}

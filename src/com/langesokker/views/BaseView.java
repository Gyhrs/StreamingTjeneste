package com.langesokker.views;

import javax.swing.*;
import java.awt.*;

public abstract class BaseView {

    protected JFrame frame;
    private String viewName;

    public BaseView(JFrame frame) {
        this.frame = frame;
    }

    public JFrame getFrame() {
        return frame;
    }

    public abstract String getViewName();

    public abstract Container getContainer();
}

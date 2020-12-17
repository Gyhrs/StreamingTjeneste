package com.langesokker.views;

import javax.swing.*;
import java.awt.*;

public abstract class BaseView {

    protected final JFrame frame;
    private final String viewName;

    public BaseView(JFrame frame, String viewName) {
        this.frame = frame;
        this.viewName = viewName;
    }

    public JFrame getFrame() {
        return frame;
    }

    /**
     * Returnere title navnet
     * @return View name
     */
    public String getViewName(){
        return this.viewName;
    }

    public abstract Container getContainer();
}

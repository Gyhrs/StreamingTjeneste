package com.langesokker.views;

import javax.swing.*;
import java.awt.*;

public abstract class BaseView {

    protected final JFrame frame;
    private final String viewName;

    /**
     * Konstrukt&oslash;r til baseview
     * @param frame = Den nuv&aelig;rende frame
     * @param viewName = Navnet p&aring; viewet
     */
    public BaseView(JFrame frame, String viewName) {
        this.frame = frame;
        this.viewName = viewName;
    }

    /**
     * Getter metode til at f&aring; framen
     * @return Den nuv&aelig;rende frame
     */
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

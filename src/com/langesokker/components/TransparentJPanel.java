package com.langesokker.components;

import javax.swing.*;
import java.awt.*;


public class TransparentJPanel extends JPanel {

    private final float panelAlfa;
    private final Color color;


    /**
     * Kontrukt&oslash;r til TransparentJPanel
     * @param panelAlfa = Alpha v&aelig;rdien p&aring; baggrunden
     * @param color = Farven p&aring; baggrunden
     */
    public TransparentJPanel(float panelAlfa, Color color) {
        this.panelAlfa = panelAlfa;
        this.color = color;
        this.setOpaque(false);
    }


    /**
     * Overridet metode
     * @param g = Graphics
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, panelAlfa));
        this.setBackground(this.color);
        super.paintComponent(g2d);

    }

    /**
     * Overridet metode
     * @param g = Graphics
     */
    @Override
    protected void paintChildren(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.color);
        g2d.fillRect(0,8,getWidth(),getHeight()-15); //8 and 15 are magic numbers. Prob because the container does not fit the image.
        g2d.setColor(getBackground());
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(
                AlphaComposite.SRC_ATOP, 1f));
        super.paintChildren(g2d);
    }
}

package com.langesokker.components;

import javax.swing.*;
import java.awt.*;

public class TransparentJPanel extends JPanel {

    private final float panelAlfa;
    private final Color color;

    public TransparentJPanel(float panelAlfa, Color color) {
        this.panelAlfa = panelAlfa;
        this.color = color;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, panelAlfa));
        this.setBackground(this.color);
        super.paintComponent(g2d);

    }

    @Override
    protected void paintChildren(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(getBackground());
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(
                AlphaComposite.SRC_ATOP, 1f));

        super.paintChildren(g);
    }
}

package com.langesokker.components;

import javax.swing.*;
import java.awt.*;

public class JText extends JLabel {

    private int fontSize;
    private boolean isBold = false;
    String fontName = "Times New Roman";
    Color color;

    public JText(String text){
        super(text);
        this.fontSize = super.getFont().getSize();
    }

    public JText(String text, Color color){
        this(text);
        this.color = color;
    }

    public JText(String text, int fontSize){
        super(text);
        this.fontSize = fontSize;
    }

    public JText(String text, int fontSize, boolean isBold){
        this(text, fontSize);
        this.isBold = isBold;
    }

    public boolean isBold() {
        return isBold;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public void setBold(boolean bold) {
        isBold = bold;
    }
    /**
     * Skaber en generel font for beskrivelserne af hvert medie, efter er medie er blevet valgt
     */
    @Override
    protected void paintComponent(Graphics g) {
        if(color != null) super.setForeground(Color.WHITE);
        Font defaultFont = super.getFont();
        super.setFont(new Font(fontName, isBold() ? Font.BOLD : Font.PLAIN, getFontSize()));
        super.paintComponent(g);
    }
}

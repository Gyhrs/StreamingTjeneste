package com.langesokker.components;

import javax.swing.*;
import java.awt.*;

public class JText extends JLabel {

    private final int fontSize;
    private boolean isBold = false;
    String fontName = "Lucida Sans";
    Color color;

    /**
     * Standard konstruktør for JText
     * @param text ønskede text
     */
    public JText(String text){
        super(text);
        this.fontSize = super.getFont().getSize();
    }

    /**
     * Overloaded konstruktør
     * @param text ønskede tekst
     * @param color farve på teksten
     */
    public JText(String text, Color color){
        this(text);
        this.color = color;
    }

    /**
     * Overloaded konstruktør
     * @param text ønskede tekst
     * @param fontSize størrelse på teksten
     */
    public JText(String text, int fontSize) {
        super(text);
        this.fontSize = fontSize;

    }

    /**
     * Overloaded konstruktør
     * @param text ønskede tekst
     * @param fontSize størrelse på tekst
     * @param color farve på tekst
     */
    public JText(String text, int fontSize, Color color){
        this(text, fontSize);
        this.color = color;
    }

    /**
     * Overloaded konstruktør
     * @param text ønskede tekst
     * @param fontSize størrelse på teksten
     * @param isBold om teksten skal være fed
     */
    public JText(String text, int fontSize, boolean isBold){
        this(text, fontSize);
        this.isBold = isBold;
    }

    /**
     * Overloaded konstruktør
     * @param text ønskede tekst
     * @param fontSize størrelse på tekst
     * @param isBold om teksten skal være fed
     * @param color farve på tekst
     */
    public JText(String text, int fontSize, boolean isBold,  Color color){
        this(text, fontSize, isBold);
        this.color = color;
    }

    public int getFontSize() {
        return fontSize;
    }

    /**
     * Skaber en generel font for beskrivelserne af hvert medie, efter et medie er blevet valgt
     */
    @Override
    protected void paintComponent(Graphics g) {
        if(color == null) super.setForeground(Color.WHITE);
        else super.setForeground(color);
        super.setFont(new Font(fontName, isBold ? Font.BOLD : Font.PLAIN, fontSize));
        super.paintComponent(g);
    }
}

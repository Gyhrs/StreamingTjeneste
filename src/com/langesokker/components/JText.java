package com.langesokker.components;

import javax.swing.*;
import java.awt.*;

public class JText extends JLabel {

    private final int fontSize;
    private boolean isBold = false;
    Color color;

    /**
     * Standard konstrukt&oslash;r for JText
     * @param text &oslash;nskede text
     */
    public JText(String text){
        super(text);
        this.fontSize = super.getFont().getSize();
    }

    /**
     * Overloaded konstrukt&oslash;r
     * @param text &oslash;nskede tekst
     * @param color farve p&aring; teksten
     */
    public JText(String text, Color color){
        this(text);
        this.color = color;
    }

    /**
     * Overloaded konstrukt&oslash;r
     * @param text &oslash;nskede tekst
     * @param fontSize st&oslash;rrelse p&aring; teksten
     */
    public JText(String text, int fontSize) {
        super(text);
        this.fontSize = fontSize;

    }

    /**
     * Overloaded konstrukt&oslash;r
     * @param text &oslash;nskede tekst
     * @param fontSize st&oslash;rrelse p&aring; tekst
     * @param color farve p&aring; tekst
     */
    public JText(String text, int fontSize, Color color){
        this(text, fontSize);
        this.color = color;
    }

    /**
     * Overloaded konstrukt&oslash;r
     * @param text &oslash;nskede tekst
     * @param fontSize st&oslash;rrelse p&aring; teksten
     * @param isBold om teksten skal v&aelig;re fed
     */
    public JText(String text, int fontSize, boolean isBold){
        this(text, fontSize);
        this.isBold = isBold;
    }

    /**
     * Overloaded konstrukt&oslash;r
     * @param text &oslash;nskede tekst
     * @param fontSize st&oslash;rrelse p&aring; tekst
     * @param isBold om teksten skal v&aelig;re fed
     * @param color farve p&aring; tekst
     */
    public JText(String text, int fontSize, boolean isBold,  Color color){
        this(text, fontSize, isBold);
        this.color = color;
    }

    /**
     *
     * @return font st&oslash;rrelsen
     */
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
        String fontName = "Lucida Sans";
        super.setFont(new Font(fontName, isBold ? Font.BOLD : Font.PLAIN, fontSize));
        super.paintComponent(g);
    }
}

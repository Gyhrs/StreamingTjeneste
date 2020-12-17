package com.langesokker.components.containers;

import javax.swing.*;
import java.awt.*;

public class JMultiLine extends JTextArea {

    private Color color;
    boolean isBold = false;
    int fontSize = 12;

    /**
     * Kontrukt&oslash;r til at lave en multiline text som wrapper
     * @param text = Indholdet
     */
    public JMultiLine(String text){
        super(text);
    }

    /**
     * Kontrukt&oslash;r til at lave en multiline text som wrapper
     * @param text = Indholdet
     * @param fontSize = St&oslash;rrelsen af teksten i pixler
     */
    public JMultiLine(String text, int fontSize){
        this(text);
        this.fontSize = fontSize;
    }
    /**
     * Kontrukt&oslash;r til at lave en multiline text som wrapper
     * @param text = Indholdet
     * @param fontSize = St&oslash;rrelsen af teksten i pixler
     * @param color = Farven p&aring; texten
     */
    public JMultiLine(String text, int fontSize, Color color){
        this(text, fontSize);
        this.color = color;
    }

    /**
     * Kontrukt&oslash;r til at lave en multiline text som wrapper
     * @param text = Indholdet
     * @param fontSize = St&oslash;rrelsen af teksten i pixler
     * @param color = Farven p&aring; texten
     * @param isBold = Skal teksten v&aelig;re med fed skrift
     */
    public JMultiLine(String text, int fontSize, Color color, boolean isBold){
        this(text, fontSize, color);
        this.isBold = isBold;
    }

    /**
     * Overrided metode fra JTextArea til at &aelig;ndre p&aring; tekst indstillingerne
     * @param g = Graphics objekt
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.setLineWrap(true);
        super.setWrapStyleWord(true);
        this.setMargin(new Insets(20, 10, 10, 10));
        this.setOpaque(false);

        if(color == null) super.setForeground(Color.WHITE);
        else super.setForeground(color);
        String fontName = "Lucida Sans";
        super.setFont(new Font(fontName, isBold ? Font.BOLD : Font.PLAIN, fontSize));
        super.paintComponent(g);
    }

}

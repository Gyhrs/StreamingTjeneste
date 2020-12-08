package com.langesokker.components.containers;

import com.langesokker.components.JText;

import javax.swing.*;
import java.awt.*;

public class JMultiLine extends JTextArea {

    Color color;
    String fontName = "Lucida Sans";
    boolean isBold = false;
    int fontSize = 12;

    public JMultiLine(String text){
        super(text);
    }
    public JMultiLine(String text, int fontSize){
        this(text);
        this.fontSize = fontSize;
    }
    public JMultiLine(String text, int fontSize, Color color){
        this(text, fontSize);
        this.color = color;
    }
    public JMultiLine(String text, int fontSize, Color color, boolean isBold){
        this(text, fontSize, color);
        this.isBold = isBold;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.setLineWrap(true);
        super.setWrapStyleWord(true);
        this.setMargin(new Insets(20, 10, 10, 10));
        this.setOpaque(false);

        if(color == null) super.setForeground(Color.WHITE);
        else super.setForeground(color);
        super.setFont(new Font(fontName, isBold ? Font.BOLD : Font.PLAIN, fontSize));
        super.paintComponent(g);
    }

}

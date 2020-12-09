package com.langesokker.components;

import javax.swing.*;
import java.awt.*;

public class ErrorPopup extends JDialog{

    public ErrorPopup(JFrame frame, String title, String message, boolean exitOnOk){
        super(frame, title);
        super.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLayout(new BorderLayout());
        //Also print message to console
        System.out.println("Error: " + message);
        this.add(new JLabel(String.format("<html><div style=\"text-align:center;\">%s</div></html>", message)), BorderLayout.CENTER);
        JButton button = new JButton(exitOnOk ? "Exit!" : "Ok!");
        button.addActionListener(e -> {
            this.setVisible(false);
            if(exitOnOk){
                System.exit(0);
            }
        });
        this.add(button, BorderLayout.SOUTH);
        this.setSize(200, 150);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}

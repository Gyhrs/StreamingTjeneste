package com.langesokker.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ErrorPopup extends JDialog{

    /**
     * Kontruktør til en ErrorPopup som er en hjælpe klasse som extender JDialog
     * @param frame = hvilken frame skal den displays i? new JFrame() for nyt vindue
     * @param title = Titlen på dialogen
     * @param message = Beskeden til brugeren
     * @param exitOnOk = Skal den lukke programmet når der er blevet trykket ok/exit?
     */
    public ErrorPopup(JFrame frame, String title, String message, boolean exitOnOk){
        super(frame, title);
        super.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                if(exitOnOk){
                    System.exit(0);
                }
            }
        };
        this.addWindowListener(exitListener);

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

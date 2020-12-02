package com.langesokker.controllers;

import com.langesokker.media.Media;
import com.langesokker.views.MediaItemView;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GUIController {

    private static GUIController instance;

    private final MediaController mediaController = MediaController.getInstance();

    private final JFrame frame;

    public GUIController(){
        frame = new JFrame("Lange sokker streaming");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static GUIController getInstance(){
        if (instance == null)  instance = new GUIController();
        return instance;
    }

    public void display(){
        frame.pack();
        frame.setVisible(true);
    }

    public void setupGUI(){
        frame.setMinimumSize(new Dimension(500, 300));
        frame.setPreferredSize(new Dimension(1920, 1000));
        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(new BorderLayout());

        Container topContainer = new Container();
        topContainer.setLayout(new FlowLayout(FlowLayout.CENTER));
        Container contentContainer = new Container();
        contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.PAGE_AXIS));

        JTextField searchbar = new JTextField();
        searchbar.setPreferredSize(new Dimension(200, 20));
        topContainer.add(searchbar);

        JButton searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(100, 20));

        topContainer.add(searchButton);
        //searchButton.addActionListener(//her skal den søge efter det der står i feltet);

        /*JButton someButton = new JButton("homdog");
        someButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showConfirmDialog(frame, "HOMDOG!!!!");
            }
        });
        contentContainer.add(someButton);*/
        contentContainer.add(updateContentContainer());


        mainPanel.add(topContainer, BorderLayout.NORTH);
        mainPanel.add(contentContainer, BorderLayout.CENTER);
        frame.add(mainPanel);

    }

    private JScrollPane updateContentContainer(){
        return updateContentContainer("");
    }

    private JScrollPane updateContentContainer(String query){
        int item = 0;
        Container container = new Container();
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
        Container rowContainer = new Container();
        rowContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
        for(List<Media> mediaList : mediaController.getMediaMap().values()){
            for (Media media : mediaList){
                if(item % 5 == 0){
                    container.add(rowContainer);
                    rowContainer = new Container();
                    rowContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));

                }
                rowContainer.add(new MediaItemView(frame, media.getName(), mediaController.getMediaImage(media)).getComponent());

                item++;
            }
        }
        if((item-1) % 5 != 0){
            container.add(rowContainer);
        }
        JScrollPane scrollPane = new JScrollPane(container, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(7);
        return scrollPane;
    }
}

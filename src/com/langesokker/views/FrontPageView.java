package com.langesokker.views;

import com.langesokker.controllers.MediaController;
import com.langesokker.media.Media;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FrontPageView extends BaseView{
    private final MediaController mediaController = MediaController.getInstance();
    private String query = "";

    public FrontPageView(JFrame frame) {
        super(frame);
    }

    @Override
    public Container getContainer() {
        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(new BorderLayout());

        Container topContainer = new Container();
        topContainer.setLayout(new FlowLayout(FlowLayout.CENTER));
        Container contentContainer = new Container();
        contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.PAGE_AXIS));

        JTextField searchbar = new JTextField(query);
        searchbar.setPreferredSize(new Dimension(200, 20));
        topContainer.add(searchbar);

        JButton searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(100, 20));

        topContainer.add(searchButton);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                query = searchbar.getText();

                contentContainer.remove(0);
                contentContainer.add(updateContentContainer(query));
                mainPanel.revalidate();
                mainPanel.repaint();

            }
        });

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
        return mainPanel;


    } /**
     * Updaterer hele panelet ud fra
     */
    private JScrollPane updateContentContainer(){
        return updateContentContainer(query);
    }
    /**
     * Updaterer containeren af medier baseret på den string af karakterer man søger efter
     */
    private JScrollPane updateContentContainer(String query){
        int item = 0;
        Container container = new Container();
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
        Container rowContainer = new Container();
        rowContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
        for(List<Media> mediaList : mediaController.getMediaMap().values()){
            for (Media media : mediaList){
                if(!media.getName().toLowerCase().contains(query.toLowerCase()) && !query.equals("")){
                    continue;
                }
                if(item % 5 == 0){
                    container.add(rowContainer);
                    rowContainer = new Container();
                    rowContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));

                }
                rowContainer.add(new MediaItemView(frame, media).getContainer());

                item++;
            }
        }

        container.add(rowContainer);

        JScrollPane scrollPane = new JScrollPane(container, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        return scrollPane;
    }

}

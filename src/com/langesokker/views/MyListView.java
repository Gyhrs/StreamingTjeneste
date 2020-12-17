package com.langesokker.views;

import com.langesokker.components.containers.NavBarContainer;
import com.langesokker.controllers.UserController;
import com.langesokker.media.Media;
import com.langesokker.media.MediaLoaderTask;
import com.langesokker.media.SupportedMediaTypes;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyListView extends BaseView {

    private final UserController userController = UserController.getInstance();

    /**
     * Kontrukt&oslash;r til MyListView
     * @param frame = Den nuv&aelig;rende frame
     */
    public MyListView(JFrame frame) {
        super(frame, "My list");
    }

    /**
     * Fylder et panel med MediaItemView af medierne i brugerens liste
     * et JScrollPane med alle medier på brugerens liste
     */
    public Container getContainer() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(15);
        Map<SupportedMediaTypes, List<Media>> availableMedia = new HashMap<>();
        for(Media media : userController.getCurrentUser().getMyList()){
            SupportedMediaTypes type = media.getType();
            List<Media> mediaList;
            if(availableMedia.containsKey(type)){
                mediaList = availableMedia.get(type);
            }else{
                mediaList = new ArrayList<>();
            }
            mediaList.add(media);
            availableMedia.put(type, mediaList);
        }
        MediaLoaderTask task = new MediaLoaderTask(this, panel, availableMedia);
        task.setErrorMessage("You have not added anything to your list yet");
        task.execute();
        mainPanel.add(new NavBarContainer(new Container()), BorderLayout.NORTH);
        mainPanel.add(panel, BorderLayout.CENTER);

        return mainPanel;
    }
}

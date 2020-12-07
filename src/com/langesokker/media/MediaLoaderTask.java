package com.langesokker.media;

import com.langesokker.components.containers.MediaItemContainer;
import com.langesokker.controllers.MediaController;
import com.langesokker.utils.Colors;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MediaLoaderTask extends SwingWorker<JScrollPane, Object> {

    Map<SupportedMediaTypes, List<Media>> availableMedia;
    String mediaTypeString;
    String genre;
    String query;

    MediaLoaderTask(Map<SupportedMediaTypes, List<Media>> availableMedia, String mediaTypeString, String genre, String query){
        this.availableMedia = availableMedia;
        this.mediaTypeString = mediaTypeString;
        this.genre = genre;
        this.query = query;
    }

    @Override
    public JScrollPane doInBackground() throws Exception {
        if(isCancelled()) return null;
        SupportedMediaTypes mediaType = null;
        try {
            mediaType = SupportedMediaTypes.valueOf(mediaTypeString);
        } catch (IllegalArgumentException ignored) {}

        int item = 0;
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBackground(Colors.SECONDARY_DARK.getColor());
        Container rowContainer = new Container();
        rowContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
        for(List<Media> mediaList : availableMedia.values()){
            for (Media media : mediaList){
                if (!mediaTypeString.trim().equals("")
                        && !mediaTypeString.equalsIgnoreCase("All media")) {
                    if (mediaType == null || !media.getType().equals(mediaType)) {
                        continue;
                    }
                }

                if (!genre.equals("")
                        && !genre.equalsIgnoreCase("All genres")) {
                    List<String> genreList = Arrays.asList(media.getGenres());
                    if(!genreList.contains(genre)) {
                        continue;
                    }
                }

                if(!media.getName().toLowerCase().contains(query.toLowerCase()) && !query.equals("")){
                    continue;
                }
                if(item % 5 == 0){
                    panel.add(rowContainer);
                    rowContainer = new Container();
                    rowContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));

                }
                rowContainer.add(new MediaItemContainer(media).getContainer());

                item++;
            }
        }

        panel.add(rowContainer);

        JScrollPane scrollPane = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        return scrollPane;
    }
}

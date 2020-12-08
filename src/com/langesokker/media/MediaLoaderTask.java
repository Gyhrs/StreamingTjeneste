package com.langesokker.media;

import com.langesokker.components.containers.MediaItemContainer;
import com.langesokker.controllers.GUIController;
import com.langesokker.utils.Colors;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MediaLoaderTask extends SwingWorker<JPanel, Container> {

    private final GUIController guiController = GUIController.getInstance();

    Map<SupportedMediaTypes, List<Media>> availableMedia;
    String mediaTypeString;
    String genre;
    String query;
    JPanel panel;
    int item = 0;

    public MediaLoaderTask(JPanel panel, Map<SupportedMediaTypes, List<Media>> availableMedia, String mediaTypeString, String genre, String query){
        this.availableMedia = availableMedia;
        this.mediaTypeString = mediaTypeString;
        this.genre = genre;
        this.query = query;

        this.panel = panel;
        this.panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        this.panel.setBackground(Colors.SECONDARY_DARK.getColor());
        guiController.getFrame().setCursor(new Cursor(Cursor.WAIT_CURSOR));
    }

    @Override
    protected void done() {
        super.done();
        guiController.getFrame().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    @Override
    public JPanel doInBackground() {
        if(isCancelled()) return null;
        SupportedMediaTypes mediaType = null;
        try {
            mediaType = SupportedMediaTypes.valueOf(mediaTypeString);
        } catch (IllegalArgumentException ignored) {}

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
                    publish(rowContainer);

                    rowContainer = new Container();
                    rowContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));

                }

                rowContainer.add(new MediaItemContainer(media));
                item++;

            }
        }

        publish(rowContainer);

        return panel;
    }
    @Override
    protected void process(List<Container> chunks) {
        for (Container container : chunks) {
            panel.add(container);
            panel.revalidate();
            panel.repaint();
        }
    }
}

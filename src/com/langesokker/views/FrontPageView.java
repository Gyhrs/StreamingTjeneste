package com.langesokker.views;

import com.langesokker.controllers.GUIController;
import com.langesokker.controllers.MediaController;
import com.langesokker.media.Media;
import com.langesokker.media.SupportedMediaTypes;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Flow;

public class FrontPageView extends BaseView{
    private final MediaController mediaController = MediaController.getInstance();
    private String query = "";
    private String preferredGenre = "";
    private String preferredMediaType = "";

    public FrontPageView(JFrame frame) {
        super(frame);
    }

    public void resetSearch(){
        this.query = "";
        this.preferredGenre = "";
        this.preferredMediaType = "";
    }

    @Override
    public Container getContainer() {
        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(new BorderLayout());

        Container topContainer = new Container();
        topContainer.setLayout(new FlowLayout(FlowLayout.CENTER));
        Container contentContainer = new Container();
        contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.PAGE_AXIS));

        JComboBox<String> mediaTypesBox = new JComboBox<>(SupportedMediaTypes.getMediaTypesArray());
        mediaTypesBox.setEditable(true);
        if(!preferredMediaType.trim().equals("")){
            mediaTypesBox.setSelectedItem(preferredMediaType);
        }
        topContainer.add(mediaTypesBox);

        JComboBox<String> genresBox = new JComboBox<>(mediaController.getKnownGenres());
        genresBox.setEditable(true);
        if(!preferredGenre.trim().equals("")){
            genresBox.setSelectedItem(preferredGenre);
        }
        topContainer.add(genresBox);

        JTextField searchbar = new JTextField(query);
        searchbar.setPreferredSize(new Dimension(200, 20));
        topContainer.add(searchbar);

        JButton searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(100, 20));

        topContainer.add(searchButton);
        searchButton.addActionListener(e -> {
            query = searchbar.getText();
            preferredGenre = (String) genresBox.getSelectedItem();
            preferredMediaType = (String) mediaTypesBox.getSelectedItem();
            contentContainer.remove(0);
            contentContainer.add(FrontPageView.this.updateContentContainer(preferredMediaType, preferredGenre, query));
            mainPanel.revalidate();
            mainPanel.repaint();

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
    }

    /**
     * Updaterer hele panelet ud fra
     */
    private JScrollPane updateContentContainer(){
        return updateContentContainer("","",query);
    }
    /**
     * Updaterer containeren af medier baseret på den string af karakterer man søger efter
     */
    private JScrollPane updateContentContainer(String mediaTypeString, String genre, String query){
        SupportedMediaTypes mediaType = null;
        try {
            mediaType = SupportedMediaTypes.valueOf(mediaTypeString);
        } catch (IllegalArgumentException iae) {}

        int item = 0;
        Container container = new Container();
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
        Container rowContainer = new Container();
        rowContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
        for(List<Media> mediaList : mediaController.getMediaMap().values()){
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

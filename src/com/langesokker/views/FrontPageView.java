package com.langesokker.views;

import com.langesokker.components.containers.NavBarContainer;
import com.langesokker.controllers.MediaController;
import com.langesokker.media.MediaLoaderTask;
import com.langesokker.media.SupportedMediaTypes;

import javax.swing.*;
import java.awt.*;

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

        Container contentContainer = new Container();
        contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.PAGE_AXIS));

        Container centerNavContainer = new Container();
        centerNavContainer.setLayout(new FlowLayout(FlowLayout.CENTER));

        JComboBox<String> mediaTypesBox = createMediaTypesBox();
        centerNavContainer.add(mediaTypesBox);

        JComboBox<String> genresBox = createGenresBox();
        centerNavContainer.add(genresBox);

        JTextField searchBar = createSearchBar();
        centerNavContainer.add(searchBar);

        JButton searchButton = createSearchButton();
        searchButton.addActionListener(e -> {
            query = searchBar.getText();
            preferredGenre = (String) genresBox.getSelectedItem();
            preferredMediaType = (String) mediaTypesBox.getSelectedItem();
            contentContainer.remove(0);
            contentContainer.add(FrontPageView.this.updateContentContainer(preferredMediaType, preferredGenre, query));
            mainPanel.revalidate();
            mainPanel.repaint();
        });
        centerNavContainer.add(searchButton);

        Container navContainer = new NavBarContainer(centerNavContainer);
        contentContainer.add(updateContentContainer());

        mainPanel.add(navContainer, BorderLayout.NORTH);
        mainPanel.add(contentContainer, BorderLayout.CENTER);
        return mainPanel;
    }

    private JComboBox<String> createMediaTypesBox() {
        JComboBox<String> mediaTypesBox = new JComboBox<>(SupportedMediaTypes.getMediaTypesArray());
        mediaTypesBox.setEditable(true);
        if(!preferredMediaType.trim().equals("")){
            mediaTypesBox.setSelectedItem(preferredMediaType);
        }
        return mediaTypesBox;
    }

    private JComboBox<String> createGenresBox() {
        JComboBox<String> genresBox = new JComboBox<>(mediaController.getKnownGenres());
        genresBox.setEditable(true);
        if(!preferredGenre.trim().equals("")){
            genresBox.setSelectedItem(preferredGenre);
        }
        return genresBox;
    }

    private JTextField createSearchBar() {
        JTextField searchbar = new JTextField(query);
        searchbar.setPreferredSize(new Dimension(200, 20));
        return searchbar;
    }

    private JButton createSearchButton() {
        JButton searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(100, 20));
        return  searchButton;
    }

    /**
     * Updaterer hele panelet ud fra
     */
    private JScrollPane updateContentContainer(){
        return updateContentContainer(preferredMediaType,preferredGenre,query);
    }

    /**
     * Updaterer containeren af medier baseret på den string af karakterer man søger efter
     */
    private JScrollPane updateContentContainer(String mediaTypeString, String genre, String query){
        JPanel panel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(15);
        MediaLoaderTask task = new MediaLoaderTask(panel, mediaController.getMediaMap(), mediaTypeString, genre, query);
        task.execute();

        return scrollPane;
    }
}

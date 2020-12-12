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
    private double preferredMinimumRating = 0.0;

    public FrontPageView(JFrame frame) {
        super(frame);
    }

    @Override
    public String getViewName() {
        return "Home";
    }

    public void resetSearch(){
        this.query = "";
        this.preferredGenre = "";
        this.preferredMediaType = "";
        this.preferredMinimumRating = 0;
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

        JComboBox<Double> ratingBox = createMinimumRatingBox();
        centerNavContainer.add(ratingBox);

        JTextField searchBar = createSearchBar();
        centerNavContainer.add(searchBar);

        JButton searchButton = createSearchButton();
        searchButton.addActionListener(e -> {
            query = searchBar.getText();
            preferredGenre = (String) genresBox.getSelectedItem();
            preferredMediaType = (String) mediaTypesBox.getSelectedItem();
            preferredMinimumRating = (double) ratingBox.getSelectedItem();
            contentContainer.remove(0);
            contentContainer.add(FrontPageView.this.updateContentContainer(preferredMediaType, preferredGenre, preferredMinimumRating, query));
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

    private JComboBox<Double> createMinimumRatingBox() {
        Double[] ratings = new Double[10];
        for (int i = 0; i < ratings.length; i++) {
            ratings[i] = (double) i;
        }
        JComboBox<Double> minimumRatingBox = new JComboBox<>(ratings);
        minimumRatingBox.setEditable(true);
        minimumRatingBox.setSelectedItem(preferredMinimumRating);
        return minimumRatingBox;
    }

    private JComboBox<String> createMediaTypesBox() {
        JComboBox<String> mediaTypesBox = new JComboBox<>(SupportedMediaTypes.getMediaTypesArray());
        if(!preferredMediaType.trim().equals("")){
            mediaTypesBox.setSelectedItem(preferredMediaType);
        }
        return mediaTypesBox;
    }

    private JComboBox<String> createGenresBox() {
        JComboBox<String> genresBox = new JComboBox<>(mediaController.getKnownGenres());
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
        return updateContentContainer(preferredMediaType,preferredGenre,preferredMinimumRating,query);
    }

    /**
     * Updaterer containeren af medier baseret på den string af karakterer man søger efter
     */
    private JScrollPane updateContentContainer(String mediaTypeString, String genre, double minimumRating, String query){
        JPanel panel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(15);
        MediaLoaderTask task = new MediaLoaderTask(panel, mediaController.getMediaMap(), mediaTypeString, genre, minimumRating, query);
        task.execute();

        return scrollPane;
    }
}

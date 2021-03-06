package com.langesokker.views;

import com.langesokker.components.containers.NavBarContainer;
import com.langesokker.controllers.MediaController;
import com.langesokker.media.MediaLoaderTask;
import com.langesokker.media.SupportedMediaTypes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FrontPageView extends BaseView{
    private final MediaController mediaController = MediaController.getInstance();
    private String query = "";
    private String preferredGenre = "";
    private String preferredMediaType = "";
    private String preferredMinimumRatingString = "0.0+";

    /**
     * Konstrukt&oslash;r til FrontPageView
     * @param frame = Det nuv&aelig;rende frame
     */
    public FrontPageView(JFrame frame) {
        super(frame, "Home");
    }

    /**
     * S&aelig;tter s&oslash;gefilteret tilbage til standard
     */
    public void resetSearch(){
        this.query = "";
        this.preferredGenre = "";
        this.preferredMediaType = "";
        this.preferredMinimumRatingString = "0.0+";
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

        JComboBox<String> ratingBox = createMinimumRatingBox();
        centerNavContainer.add(ratingBox);

        JTextField searchBar = createSearchBar();
        searchBar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) search(searchBar, genresBox, mediaTypesBox, ratingBox, contentContainer, mainPanel);}
        });
        centerNavContainer.add(searchBar);

        JButton searchButton = createSearchButton();
        searchButton.addActionListener(e -> search(searchBar, genresBox, mediaTypesBox, ratingBox, contentContainer, mainPanel));
        centerNavContainer.add(searchButton);

        Container navContainer = new NavBarContainer(centerNavContainer);
        contentContainer.add(updateContentContainer());

        mainPanel.add(navContainer, BorderLayout.NORTH);
        mainPanel.add(contentContainer, BorderLayout.CENTER);
        return mainPanel;
    }

    /**
     * Hj&aelig;lpe metode til at s&oslash;ge
     * @param searchBar S&oslash;gefeltet
     * @param genresBox Genre v&aelig;lgeren
     * @param mediaTypesBox Medietype v&aelig;lgeren
     * @param ratingBox Minimum rating v&aelig;lgeren
     * @param contentContainer Indholds containeren
     * @param mainPanel Hovedpanelet
     */
    private void search(JTextField searchBar, JComboBox<String> genresBox,JComboBox<String> mediaTypesBox,JComboBox<String> ratingBox, Container contentContainer, JPanel mainPanel){
        query = searchBar.getText();
        preferredGenre = (String) genresBox.getSelectedItem();
        preferredMediaType = (String) mediaTypesBox.getSelectedItem();
        preferredMinimumRatingString = (String) ratingBox.getSelectedItem();
        contentContainer.remove(0);
        contentContainer.add(FrontPageView.this.updateContentContainer(preferredMediaType, preferredGenre, getMinimumRating(), query));
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    /**
     * Genererer en JComboBox til ratings
     * @return en JComboBox med ratings muligheder
     */
    private JComboBox<String> createMinimumRatingBox() {
        String[] ratings = new String[10];
        for (int i = 0; i < ratings.length; i++) {
            ratings[i] = (double) i + "+";
        }
        JComboBox<String> minimumRatingBox = new JComboBox<>(ratings);
        minimumRatingBox.setSelectedItem(preferredMinimumRatingString);
        return minimumRatingBox;
    }

    /**
     * Genererer en JComboBox til medietyper
     * @return en JComboBox med medietyper
     */
    private JComboBox<String> createMediaTypesBox() {
        JComboBox<String> mediaTypesBox = new JComboBox<>(SupportedMediaTypes.getMediaTypesArray());
        if(!preferredMediaType.trim().equals("")){
            mediaTypesBox.setSelectedItem(preferredMediaType);
        }
        return mediaTypesBox;
    }

    /**
     * Genererer en JComboBox til genre
     * @return en JComboBox med genre muligheder
     */
    private JComboBox<String> createGenresBox() {
        JComboBox<String> genresBox = new JComboBox<>(mediaController.getKnownGenres());
        if(!preferredGenre.trim().equals("")){
            genresBox.setSelectedItem(preferredGenre);
        }
        return genresBox;
    }

    /**
     * Genererer en s&oslash;gefelt
     * @return et s&oslash;gefelt
     */
    private JTextField createSearchBar() {
        JTextField searchbar = new JTextField(query);
        searchbar.setPreferredSize(new Dimension(200, 20));
        return searchbar;
    }

    /**
     * Generer en s&oslash;ge knap
     * @return Knap til at klikke s&oslash;g p&aring;
     */
    private JButton createSearchButton() {
        JButton searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(100, 20));
        return  searchButton;
    }

    /**
     * Updaterer hele panelet ud fra standard parameter
     * @return JScrollPane med indholdet fra s&oslash;gningen
     */
    private JScrollPane updateContentContainer(){
        return updateContentContainer(preferredMediaType,preferredGenre, getMinimumRating(),query);
    }

    /**
     *
     * Updaterer containeren af medier baseret p&aring; den string af karakterer man s&oslash;ger efter og &oslash;nskede minimum rating
     * @param mediaTypeString = Typen mediet skal v&aelig;re
     * @param genre = Genren mediet skal have
     * @param minimumRating = Den mindste rating mediet skal have
     * @param query = S&oslash;gefeltet
     * @return en JScrollPane med indholdet fra s&oslash;gningen
     */
    private JScrollPane updateContentContainer(String mediaTypeString, String genre, double minimumRating, String query){
        JPanel panel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(15);
        MediaLoaderTask task = new MediaLoaderTask(this, panel, mediaController.getMediaMap(), mediaTypeString, genre, minimumRating, query);
        task.execute();
        return scrollPane;
    }

    /**
     * Getter metode til at f&aring; rating uden "+"
     * @return Ratingen der skal s&oslash;ges med
     */
    private double getMinimumRating(){

        double minimumRating;
        try{
            minimumRating = Double.parseDouble(preferredMinimumRatingString.replace("+", ""));
        }catch (NumberFormatException e){
            minimumRating = 0;
        }
        return minimumRating;
    }
}

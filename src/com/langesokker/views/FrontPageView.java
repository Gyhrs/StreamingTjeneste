package com.langesokker.views;

import com.langesokker.components.containers.MediaItemContainer;
import com.langesokker.components.containers.NavBarContainer;
import com.langesokker.controllers.MediaController;
import com.langesokker.media.Media;
import com.langesokker.media.MediaLoaderTask;
import com.langesokker.media.SupportedMediaTypes;
import com.langesokker.utils.Colors;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

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

        JComboBox<String> mediaTypesBox = new JComboBox<>(SupportedMediaTypes.getMediaTypesArray());
        mediaTypesBox.setEditable(true);
        if(!preferredMediaType.trim().equals("")){
            mediaTypesBox.setSelectedItem(preferredMediaType);
        }
        centerNavContainer.add(mediaTypesBox);

        JComboBox<String> genresBox = new JComboBox<>(mediaController.getKnownGenres());
        genresBox.setEditable(true);
        if(!preferredGenre.trim().equals("")){
            genresBox.setSelectedItem(preferredGenre);
        }
        centerNavContainer.add(genresBox);

        JTextField searchbar = new JTextField(query);
        searchbar.setPreferredSize(new Dimension(200, 20));
        centerNavContainer.add(searchbar);

        JButton searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(100, 20));

        centerNavContainer.add(searchButton);
        searchButton.addActionListener(e -> {
            query = searchbar.getText();
            preferredGenre = (String) genresBox.getSelectedItem();
            preferredMediaType = (String) mediaTypesBox.getSelectedItem();
            contentContainer.remove(0);
            contentContainer.add(FrontPageView.this.updateContentContainer(preferredMediaType, preferredGenre, query));
            mainPanel.revalidate();
            mainPanel.repaint();
        });
        Container navContainer = new NavBarContainer(centerNavContainer).getContainer();
        /*JButton someButton = new JButton("homdog");
        someButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showConfirmDialog(frame, "HOMDOG!!!!");
            }
        });
        contentContainer.add(someButton);*/
        contentContainer.add(updateContentContainer());

        mainPanel.add(navContainer, BorderLayout.NORTH);
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
        JPanel panel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(15);
        MediaLoaderTask task = new MediaLoaderTask(panel, mediaController.getMediaMap(), mediaTypeString, genre, query);
        task.execute();
        return scrollPane;
    }
}

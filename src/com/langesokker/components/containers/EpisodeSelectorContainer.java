package com.langesokker.components.containers;

import com.langesokker.components.JText;
import com.langesokker.media.Seasonable;

import javax.swing.*;
import java.awt.*;

public class EpisodeSelectorContainer extends JPanel{

    public EpisodeSelectorContainer(Seasonable seasonable){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setOpaque(false);
        this.setMaximumSize(new Dimension(200, 60));
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setAlignmentY(Component.TOP_ALIGNMENT);
        Container seasonContainer = new Container();
        seasonContainer.setLayout(new BoxLayout(seasonContainer, BoxLayout.X_AXIS));
        seasonContainer.add(new JText("Season:", 15, true, Color.WHITE));
        Integer[] seasons = seasonable.getSeasons().keySet().toArray(new Integer[]{});
        JComboBox<Integer> seasonsBox = new JComboBox<>(seasons);
        seasonContainer.add(seasonsBox);
        this.add(seasonContainer);

        Container episodeContainer = new Container();
        episodeContainer.setLayout(new BoxLayout(episodeContainer, BoxLayout.X_AXIS));
        episodeContainer.add(new JText("Episode:", 15, true, Color.WHITE));
        Integer[] episodes = seasonable.getEpisodesInSeason(seasonsBox.getSelectedItem() != null? (int) seasonsBox.getSelectedItem() : 1);
        JComboBox<Integer> episodesBox = new JComboBox<>(episodes);
        episodeContainer.add(episodesBox);
        this.add(episodeContainer);

        seasonsBox.addItemListener(e -> {
            int season = (int) e.getItem();
            episodesBox.removeAllItems();
            Integer[] episodesInSeason = seasonable.getEpisodesInSeason(season);
            episodesBox.setModel(new DefaultComboBoxModel<>(episodesInSeason));
        });

    }

}

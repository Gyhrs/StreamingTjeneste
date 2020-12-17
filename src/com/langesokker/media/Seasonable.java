package com.langesokker.media;

import java.util.Map;

public interface Seasonable {

    Map<Integer, Integer> getSeasons();

    int getEndDate();

    boolean isStillAiring();

    Integer[] getEpisodesInSeason(int seasonNum);

}

package com.langesokker.media;

import java.util.List;
import java.util.Map;

public class Series extends Media{
    Map<Integer, Integer> seasons;
    int endDate;
    boolean isStillRunning = false;

    public Series(String name, int releaseDate, int endDate, boolean isStillRunning,  String[] genre, double rating, Map<Integer, Integer> seasons){
        super(name, releaseDate, genre, rating);
        this.endDate = endDate;
        this.isStillRunning = isStillRunning;
        this.seasons = seasons;
    }

    public Map<Integer, Integer> getSeasons() {
        return seasons;
    }

    public void setSeasons(Map<Integer, Integer> seasons) {
        this.seasons = seasons;
    }

    public int getEndDate() {
        return endDate;
    }

    public boolean isStillRunning() {
        return isStillRunning;
    }

    @Override
    public SupportedMediaTypes getType() {
        return SupportedMediaTypes.SERIES;
    }

    public Integer[] getEpisodesInSeason(int seasonNum) {
        int episodes = seasons.get(seasonNum);
        Integer[] episodeArray = new Integer[episodes];
        for (int i = 0; i<episodes; i++) {
            episodeArray[i] = i+1;
        }
        return episodeArray;
    }
}

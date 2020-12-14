package com.langesokker.media;

import java.util.Map;

public class Series extends Media implements Seasonable{
    Map<Integer, Integer> seasons;
    private final int endDate;
    private final boolean isStillAiring;

    public Series(String name, int releaseDate, int endDate, boolean isStillAiring, String[] genre, double rating, Map<Integer, Integer> seasons){
        super(name, releaseDate, genre, rating);
        this.endDate = endDate;
        this.isStillAiring = isStillAiring;
        this.seasons = seasons;
    }

    @Override
    public Map<Integer, Integer> getSeasons() {
        return seasons;
    }
    @Override
    public void setSeasons(Map<Integer, Integer> seasons) {
        this.seasons = seasons;
    }

    @Override
    public int getEndDate() {
        return endDate;
    }

    @Override
    public boolean isStillAiring() {
        return isStillAiring;
    }

    @Override
    public SupportedMediaTypes getType() {
        return SupportedMediaTypes.SERIES;
    }

    @Override
    public Integer[] getEpisodesInSeason(int seasonNum) {
        int episodes = seasons.get(seasonNum);
        Integer[] episodeArray = new Integer[episodes];
        for (int i = 0; i<episodes; i++) {
            episodeArray[i] = i+1;
        }
        return episodeArray;
    }
}

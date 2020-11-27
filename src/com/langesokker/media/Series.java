package com.langesokker.media;

import java.util.List;
import java.util.Map;

public class Series extends Media{
    Map<Integer, Integer> seasons;

    public Series(String name, int releaseData,  List<String> genre, double rating, String description, Map<Integer, Integer> seasons){
        super(name, releaseData, genre, rating, description);
    }

    public Map<Integer, Integer> getSeasons() {
        return seasons;
    }

    public void setSeasons(Map<Integer, Integer> seasons) {
        this.seasons = seasons;
    }
}

package com.langesokker.media;

import java.util.List;

public class Film extends Media{
    public Film(String name, List<String> genre, int releaseData, double rating, String description){
        super(name, genre, releaseData, rating, description);
    }

}

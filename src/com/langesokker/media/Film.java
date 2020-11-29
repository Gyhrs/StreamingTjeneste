package com.langesokker.media;

import java.util.List;

public class Film extends Media{

    public Film(String name, int releaseDate,  String[] genre, double rating, String description){
        super(name, releaseDate, genre, rating, description);
    }

    @Override
    public SupportedMediaTypes getType() {
        return SupportedMediaTypes.FILM;
    }

}

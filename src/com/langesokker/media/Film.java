package com.langesokker.media;

public class Film extends Media{

    public Film(String name, int releaseDate,  String[] genre, double rating){
        super(name, releaseDate, genre, rating);
    }

    @Override
    public SupportedMediaTypes getType() {
        return SupportedMediaTypes.FILM;
    }

}

package com.langesokker.media;

import java.util.List;

public class Film extends Media{
    public Film(String name, int releaseData,  List<String> genre, double rating, String description){
        super(name, releaseData, genre, rating, description);
    }

}

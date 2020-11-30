package com.langesokker.media;

import java.util.Arrays;
import java.util.List;

public abstract class Media {
    protected String name;
    protected String[] genre;
    protected int releaseDate;
    protected double rating;

    public Media(String name, int releaseDate, String[] genre, double rating) {
        this.name = name;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.rating = rating;
    }

    public double getRating() {
        return rating;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    public String[] getGenre() {
        return genre;
    }

    public String getName() {
        return name;
    }

    public void setGenre(String[] genre) {
        this.genre = genre;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }

    public abstract SupportedMediaTypes getType();

    @Override
    public String toString() {
        return "Media{" +
                "type=" + getType() +
                ", name=" + name +
                ", genre='" + Arrays.toString(genre) + '\'' +
                ", release date=" + releaseDate +
                ", rating=" + rating +
                '}';
    }
}
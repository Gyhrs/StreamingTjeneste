package com.langesokker.media;

import java.util.List;

public abstract class Media {
    protected String name;
    protected List<String> genre;
    protected int releaseDate;
    protected double rating;
    protected String description;

    public Media(String name, int releaseData, List<String> genre, double rating, String description) {
        this.name = name;
        this.genre = genre;
        this.releaseDate = releaseData;
        this.rating = rating;
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    public List<String> getGenre() {
        return genre;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGenre(List<String> genre) {
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

    @Override
    public String toString() {
        return "Media{" +
                "name=" + name +
                ", genre='" + genre + '\'' +
                ", releaseData=" + releaseDate +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                '}';
    }
}

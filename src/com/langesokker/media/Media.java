package com.langesokker.media;

import java.util.List;

public abstract class Media {
    protected String name;
    protected List<String> genre;
    protected int releaseData;
    protected double rating;
    protected String description;

    public Media(String name, List<String> genre, int releaseData, double rating, String description) {
        this.name = name;
        this.genre = genre;
        this.releaseData = releaseData;
        this.rating = rating;
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public int getReleaseData() {
        return releaseData;
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

    public void setReleaseData(int releaseData) {
        this.releaseData = releaseData;
    }

    @Override
    public String toString() {
        return "Media{" +
                "name=" + name +
                ", genre='" + genre + '\'' +
                ", releaseData=" + releaseData +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                '}';
    }
}

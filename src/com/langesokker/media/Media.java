package com.langesokker.media;

import java.util.Arrays;
import java.util.StringJoiner;

public abstract class Media {
    protected String name;
    protected String[] genres;
    protected int releaseDate;
    protected double rating;
    protected final boolean inList;

    public Media(String name, int releaseDate, String[] genre, double rating) {
        this.name = name;
        this.genres = genre;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.inList = false;
    }

    public double getRating() {
        return rating;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    public String[] getGenres() {
        return genres;
    }

    public String getName() {
        return name;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
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

    public abstract String getDateString();

    @Override
    public String toString() {
        return "Media{" +
                "type=" + getType() +
                ", name=" + name +
                ", genre='" + Arrays.toString(genres) + '\'' +
                ", release date=" + releaseDate +
                ", rating=" + rating +
                '}';
    }
    /**
        * Samler alle genre fra genre arrayet og sætter , + mellemrum imellem hver string fra arrayet
     */
    public String genresToString(){
        StringJoiner sj = new StringJoiner(", ");

        //Alternative: Arrays.stream(genres).forEach(sj::add);

        for(String s1 : genres){
            sj.add(s1);
        }
        return sj.toString();
    }
}
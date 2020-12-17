package com.langesokker.media;

import java.util.Arrays;
import java.util.StringJoiner;

public abstract class Media {
    protected String name;
    protected String[] genres;
    protected int releaseDate;
    protected double rating;
    protected final boolean inList;

    /**
     * Kontrukt&oslash;r af Media
     * @param name = Navnet p&aring; mediet
     * @param releaseDate = Udgivelses&aring;ret
     * @param genre = Genrene som passer til mediet
     * @param rating = Anmeldelsen af mediet
     */
    public Media(String name, int releaseDate, String[] genre, double rating) {
        this.name = name;
        this.genres = genre;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.inList = false;
    }

    /**
     * Getter metode til at få anmeldelsen af mediet
     * @return Double - Anmeldelsen
     */
    public double getRating() {
        return rating;
    }

    /**
     * Getter metode til at få genrene som passer til mediet
     * @return String[] - Array af genre
     */
    public String[] getGenres() {
        return genres;
    }

    /**
     * Getter metode til at få navnet på mediet
     * @return String - Navn
     */
    public String getName() {
        return name;
    }

    /**
     * Getter metode til at f&aring; medietypen
     * @return SupportedMediaTypes
     */
    public abstract SupportedMediaTypes getType();

    /**
     * Getter metode til at f&aring; datoer som er vigtige for filme
     * @return releaseDate += "x"
     */
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
     * @return List of genres
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
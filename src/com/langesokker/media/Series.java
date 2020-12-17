package com.langesokker.media;

import java.util.Map;

public class Series extends Media implements Seasonable{
    Map<Integer, Integer> seasons;
    private final int endDate;
    private final boolean isStillAiring;

    /**
     * Konstrukt&oslash;r til Series
     * @param name = Navnet p&aring; serien
     * @param releaseDate = Udgivelses&aring;r
     * @param endDate = Slutdato
     * @param isStillAiring = Er serien afsluttet
     * @param genre = Series genre
     * @param rating = Anmeldelse af serien
     * @param seasons = S&aelig;soner og episoder i serien
     */
    public Series(String name, int releaseDate, int endDate, boolean isStillAiring, String[] genre, double rating, Map<Integer, Integer> seasons){
        super(name, releaseDate, genre, rating);
        this.endDate = endDate;
        this.isStillAiring = isStillAiring;
        this.seasons = seasons;
    }

    /**
     * Map af s&aelig;soner (Key) og episoder (Value)
     * @return Map med interger som key og value
     */
    @Override
    public Map<Integer, Integer> getSeasons() {
        return seasons;
    }

    /**
     * Slut datoen
     * @return Slut datoen eller 0 hvis ingen slutdato findes
     */
    @Override
    public int getEndDate() {
        return endDate;
    }

    /**
     *
     * @return boolean om serien er blevet afsluttet
     */
    @Override
    public boolean isStillAiring() {
        return isStillAiring;
    }

    /**
     * Getter metode til at f&aring; medietypen
     * @return SupportedMediaTypes.SERIES
     */
    @Override
    public SupportedMediaTypes getType() {
        return SupportedMediaTypes.SERIES;
    }

    /**
     * Getter metode til at f&aring; datoer som er vigtige for filme
     * @return releaseDate - Enddate
     */
    @Override
    public String getDateString() {
        return releaseDate + "-" +  (isStillAiring ? "" : endDate);
    }

    /**
     * Giver en arry af episoder som kan bruges i en JComboBox
     * @param seasonNum S&aelig;son nummeret
     * @return En array af episoder nummere i en s&aelig;son
     */
    @Override
    public Integer[] getEpisodesInSeason(int seasonNum) {
        int episodes = seasons.get(seasonNum);
        Integer[] episodeArray = new Integer[episodes];
        for (int i = 0; i<episodes; i++) {
            episodeArray[i] = i+1;
        }
        return episodeArray;
    }
}

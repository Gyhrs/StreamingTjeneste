package com.langesokker.media;

import java.util.Map;

/**
 * Et interface som hj&aelig;lper med at vide om et medie har s&aelig;soner eller ej
 */
public interface Seasonable {


    /**
     * Map af s&aelig;soner (Key) og episoder (Value)
     * @return Map med Integer som key og værdi
     */
    Map<Integer, Integer> getSeasons();

    /**
     * Slut datoen
     * @return Slut datoen eller 0 hvis ingen slutdato findes
     */
    int getEndDate();

    /**
     *
     * @return boolean om serien er blevet afsluttet
     */
    boolean isStillAiring();

    /**
     * Giver en arry af episoder som kan bruges i en JComboBox
     * @param seasonNum S&aelig;son nummeret
     * @return En array af episoder nummere i en s&aelig;son
     */
    Integer[] getEpisodesInSeason(int seasonNum);

}

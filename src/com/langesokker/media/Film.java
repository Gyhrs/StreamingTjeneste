package com.langesokker.media;

public class Film extends Media{

    /**
     * Kontrukt&oslash;r til et Film medie
     * @param name = Navnet p&aring; filmen
     * @param releaseDate = Udgivelses datoen
     * @param genre = En liste af genre som passer til filmen
     * @param rating = Anmeldelsen af filmen
     */
    public Film(String name, int releaseDate,  String[] genre, double rating){
        super(name, releaseDate, genre, rating);
    }

    /**
     * Getter metode til at f&aring; medietypen
     * @return SupportedMediaTypes.FILM
     */
    @Override
    public SupportedMediaTypes getType() {
        return SupportedMediaTypes.FILM;
    }

    /**
     * Getter metode til at f&aring; udgivelsesdatoen
     * @return releaseDate
     */
    @Override
    public String getDateString() {
        return releaseDate + "";
    }

}

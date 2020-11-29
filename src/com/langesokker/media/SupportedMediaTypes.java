package com.langesokker.media;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Denne enum register hvilke typer af medier som vores program supporter.
 * TODO: SE OM NOGLE AF METHODERNE KAN FORKORTES ELLER DELE KODE
 */
public enum SupportedMediaTypes {

    FILM("film", data -> {
        String name = data[0];
        int releaseDate;
        try {
            releaseDate = Integer.parseInt(data[1].replaceAll("\\s+", ""));
        }catch (NumberFormatException e){
            System.out.println(data[1] + " is not a number");
            System.out.println(e.getMessage());
            return null;
        }
        String[] genres = data[2].replaceAll("\\s+", "").split(",");
        double rating;
        try {
            NumberFormat format = NumberFormat.getInstance(Locale.FRANCE); //Brug , i stedet for .
            Number number = format.parse(data[3].replaceAll("\\s+", ""));
            rating = number.doubleValue();
        }catch (NumberFormatException | ParseException e){
            System.out.println(data[3] + " is not a number");
            System.out.println(e.getMessage());
            return null;
        }
        return new Film(name, releaseDate, genres, rating, "");
    }),
    SERIES("series", data -> {
        String name = data[0];
        String[] dates = data[1].replaceAll("\\s+", "").split("-");

        int releaseDate;
        int endDate = 0;
        boolean isStillRunning = true;
        try {
            releaseDate = Integer.parseInt(dates[0]);
            if(dates.length > 1){
                endDate = Integer.parseInt(dates[1]);
                isStillRunning = false;
            }
        }catch (NumberFormatException e){
            System.out.println(Arrays.toString(dates) +  " contains a non-number");
            System.out.println(e.getMessage());
            return null;
        }
        String[] genres = data[2].replaceAll("\\s+", "").split(",");
        double rating;
        try {
            NumberFormat format = NumberFormat.getInstance(Locale.FRANCE); //Brug , i stedet for .
            Number number = format.parse(data[3].replaceAll("\\s+", ""));
            rating = number.doubleValue();
        }catch (NumberFormatException | ParseException e){
            System.out.println(data[3] + " is not a number");
            System.out.println(e.getMessage());
            return null;
        }
        Map<Integer, Integer> seasons = new HashMap<>();
        for(String season : data[4].replaceAll("\\s+", "").split(",")){
            String[] split = season.split("-");
            if(split.length <= 1) continue; //Not enough data.
            try{
                seasons.put(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
            }catch (NumberFormatException e){
                System.out.println(season + " contains a non-number");
                System.out.println(e.getMessage());
                return null;
            }
        }
        return new Series(name, releaseDate, endDate, isStillRunning, genres, rating, "", seasons);
    });

    /**
     * Koden til at generere den specifikke medie type
     */
    private final MediaGenerator generator;

    /**
     * Navnet for mappen med billederne/thumbnails for denne type medier
     */
    private String imageFolderName;

    SupportedMediaTypes(String imageFolderName, MediaGenerator generator){
        this.imageFolderName = imageFolderName;
        this.generator = generator;
    }

    /**
     * Konveretere dataen til et medie objekt
     * @param data = Data fra tekst filerne
     * @return Media
     */
    public Media toMedia(String[] data){
        return generator.generate(data);
    }

    public String getImageFolderName() {
        return imageFolderName;
    }

    public void setImageFolderName(String imageFolderName) {
        this.imageFolderName = imageFolderName;
    }

    /**
     * En callback interface som bruges til at skabe forskellige måder at generere medier på
     */
    public interface MediaGenerator{
        Media generate(String[] data);
    }
}

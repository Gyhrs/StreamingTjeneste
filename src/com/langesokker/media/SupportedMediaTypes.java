package com.langesokker.media;

import java.util.*;

/**
 * Denne enum register hvilke typer af medier som vores program supporter.
 */
public enum SupportedMediaTypes {

    FILM("film.txt","film", (name, releaseDate, endDate, genre, rating, extraData) -> {
        return new Film(name, releaseDate, genre, rating);
    }),

    SERIES("series.txt","series", (name, releaseDate, endDate, genre, rating, extraData) -> {
        boolean airing = true;
        if (endDate > 0) {
            airing = false;
        }

        Map<Integer, Integer> seasons = new HashMap<>();
        for(String season : extraData[0].replaceAll("\\s+", "").split(",")){
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
        return new Series(name, releaseDate, endDate, airing, genre, rating, seasons);
    });

    /**
     * Koden til at generere den specifikke medie type
     */
    private final MediaGenerator generator;

    private String fileName;

    /**
     * Navnet for mappen med billederne/thumbnails for denne type medier
     */
    private String imageFolderName;

    /**
     * Konstruktør
     * @param imageFolderName = mappen med den ønskede medietype;
     * @param generator = variable til at kunne skelne mellem de forskellige medietyper
     */
    SupportedMediaTypes(String fileName, String imageFolderName, MediaGenerator generator){
        this.fileName = fileName;
        this.imageFolderName = imageFolderName;
        this.generator = generator;
    }

    /**
     * Konveretere dataen til et medie objekt med ekstra data der kan behandles i dens egen enum
     * @param data = Data fra tekst filerne
     * @return Media
     */
    public Media toMedia(String[] data){
        String name = data[0];
        String[] dates = data[1].replaceAll("\\s+", "").split("-");
        int releaseDate = 0;
        try {
            releaseDate = Integer.parseInt(dates[0]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        int endDate = 0;
        try {
            if (dates.length > 1) {
                endDate = Integer.parseInt(dates[1]);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        String[] genres = data[2].replaceAll("\\s+", "").split(",");
        double rating = 0;
        try {
            rating = Double.parseDouble(data[3].replaceAll(",", "."));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        String[] extraData = new String[]{};
        if (data.length > 4) {
            extraData = Arrays.copyOfRange(data, 4,data.length);
        }
        return generator.generate(name, releaseDate, endDate, genres, rating, extraData);
    }

    public String getImageFolderName() {
        return imageFolderName;
    }

    public void setImageFolderName(String imageFolderName) {
        this.imageFolderName = imageFolderName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * En callback interface som bruges til at skabe forskellige måder at generere medier på
     */
    public interface MediaGenerator{
        Media generate(String name, int releaseDate, int endDate, String[] genre, double rating, String[] extraData);
    }

    public static String[] getMediaTypesArray() {
        List<String> mediaTypes = new ArrayList<>();
        mediaTypes.add("All media");
        for (SupportedMediaTypes types : SupportedMediaTypes.values()) {
            mediaTypes.add(types.name());
        }
        return mediaTypes.toArray(new String[]{});
    }
}

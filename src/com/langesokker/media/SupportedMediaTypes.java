package com.langesokker.media;

import java.util.*;

/**
 * Denne enum register hvilke typer af medier som vores program supporter.
 */
public enum SupportedMediaTypes {

    /**
     * Medietypen "film".
     * Fil navn: film.txt
     * Billede mappe navn: "film"
     * Ekstra data: Ingen
     */
    FILM("film.txt","film", (name, releaseDate, endDate, genre, rating, extraData) -> {
        return new Film(name, releaseDate, genre, rating);
    }),
    /**
     * Medietypen "serier".
     * Fil navn: series.txt
     * Billede mappe navn: "series"
     * Ekstra data: Om den er afsluttet. Hvis ja, hvorn&aring;r den blev afsluttet og s&aelig;soner og episoder nummere
     */
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
    }),
    TEST("test.txt","", (name, releaseDate, endDate, genre, rating, extraData) -> {
        return new Film(name, releaseDate, genre, rating);
    }, true);

    /**
     * Koden til at generere den specifikke medie type
     */
    private final MediaGenerator generator;

    private String fileName;
    /**
     * Den her boolean bestemmer om typen skal automatisk loades. Det bruges til at ingnorere test typen
     */
    private boolean ignoreLoad;

    /**
     * Navnet for mappen med billederne/thumbnails for denne type medier
     */
    private final String imageFolderName;

    /**
     * Konstrukt&oslash;r
     * @param fileName navnet på data filen
     * @param imageFolderName = mappen med den &oslash;nskede medietype;
     * @param generator = variable til at kunne skelne mellem de forskellige medietyper
     */
    SupportedMediaTypes(String fileName, String imageFolderName, MediaGenerator generator){
        this.fileName = fileName;
        this.imageFolderName = imageFolderName;
        this.generator = generator;
    }

    /**
     * Overloadet Konstrukt&oslash;r
     * @param fileName = Navnet på data filen
     * @param imageFolderName = mappen med den &oslash;nskede medietype;
     * @param generator = variable til at kunne skelne mellem de forskellige medietyper
     * @param ignoreLoad = om systemet skal ignore typen når programmet loades
     */
    SupportedMediaTypes(String fileName, String imageFolderName, MediaGenerator generator, boolean ignoreLoad){
        this(fileName, imageFolderName, generator);
        this.ignoreLoad = ignoreLoad;
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

    /**
     * Getter til at f&aring; billede mappe navnet
     * @return navnet for mappen der indeholder billederne der skal bruges
     */
    public String getImageFolderName() {
        return imageFolderName;
    }

    /**
     * Getter til fil navnet
     * @return Navnet p&aring; filen med dataen om medietypen
     */
    public String getFileName() {
        return fileName;
    }


    /**
     * En callback interface som bruges til at skabe forskellige m&aring;der at generere medier p&aring;
     */
    public interface MediaGenerator{
        Media generate(String name, int releaseDate, int endDate, String[] genre, double rating, String[] extraData);
    }

    /**
     * En getter metode til JComboBox
     * @return en array af medie type navne + "All media"
     */
    public static String[] getMediaTypesArray() {
        List<String> mediaTypes = new ArrayList<>();
        mediaTypes.add("All media");
        for (SupportedMediaTypes types : SupportedMediaTypes.values()) {
            if(types.ignoreLoad) continue;
            mediaTypes.add(types.name());
        }
        return mediaTypes.toArray(new String[]{});
    }

    /**
     * Se variablen ignoreload
     * @return = Om MediaControlleren automatisk skal ignorere typen. Dette bruges til test. Hvis TRUE s&aring; vil den ikke automatisk blive loadet.
     */
    public boolean shouldIgnoreLoad(){
        return this.ignoreLoad;
    }
}

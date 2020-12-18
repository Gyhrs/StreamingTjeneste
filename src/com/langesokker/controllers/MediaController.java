package com.langesokker.controllers;

import com.langesokker.components.ErrorPopup;
import com.langesokker.exceptions.ImageNotFoundException;
import com.langesokker.media.Media;
import com.langesokker.media.SupportedMediaTypes;
import com.langesokker.utils.ImageUtils;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class MediaController {
    private static MediaController instance;
    private final Map<SupportedMediaTypes, List<Media>> mediaMap;
    private final List<String> knownGenres;
    private BufferedImage thumbnailNotFound;

    /**
     * Konstrukt&oslash;r af MediaController
     */
    public MediaController(){
        this.mediaMap = new HashMap<>();
        this.knownGenres = new ArrayList<>();
        this.knownGenres.add("All genres");

        try{
            thumbnailNotFound = ImageUtils.getImage("assets/no-image.png");
        }catch(ImageNotFoundException e){
            thumbnailNotFound = null;
        }
    }

    /**
     * Laver en singleton af MediaControlleren hvis den ikke findes i forvejen
     * @return = MediaController singletong
     */
    public static MediaController getInstance() {
        if(instance == null) instance = new MediaController();
        return instance;
    }

    /**
     * Tilf&oslash;jer et medie til listen af film
     * @param type = typen = medie typen
     * @param media = Det medie man gerne vil tilf&oslash;je til mappen
     */
    public void addMedia(SupportedMediaTypes type, Media media){
        List<Media> medias;
        if(mediaMap.containsKey(type)){
            medias = mediaMap.get(type);
        }else{
            medias = new ArrayList<>();
        }
        medias.add(media);
        this.mediaMap.put(type, medias);
    }

    /**
     * Fjerner et medie  fra listen af film
     * @param media = Det medie man gerne vil fjerne fra listen
     */
    public void removeMedia(Media media){
        if(containsMedia(media)) mediaMap.get(media.getType()).remove(media);
    }

    /**
     * Denne methode checker f&oslash;rst om typen findes og bagefter g&aring;r den igennem listen af mediaer.
     * @param media = Det valgte medie man gerne vil finde
     * @return boolean. Medie findes ? TRUE : FALSE;
     */
    public boolean containsMedia(Media media){
        if(!mediaMap.containsKey(media.getType())) return false;
        return mediaMap.get(media.getType()).contains(media);
    }

    /**
     * Brug denne methode til at f&aring; det tilh&oslash;rene billede til mediet, hvis billedet findes.
     * @param media = Mediet der skal f&aring;s et billede fra
     * @return = BufferedImage ud fra /data/images/[mediaType]/[mediaNavn].jpg Kan returnere null hvis intet billed findes.
     */
    public BufferedImage getMediaImage(Media media) {
        try{

            return ImageUtils.getImage("data/images/" + media.getType().getImageFolderName() + "/" + media.getName() + ".jpg");
        }catch(ImageNotFoundException e){
            System.out.println(e.getMessage() + " at " + e.getPath());
            return thumbnailNotFound;
        }
    }

    /**
     * Denne funktion g&aring;r igennem alle filerne i /data/media/ mappen og indl&aelig;ser dem med MediaController#loadFile(String fileName) funktionen.
     */
    public void loadAllMediaTypes() {
        for(SupportedMediaTypes mediaType : SupportedMediaTypes.values()){
            if(mediaType.shouldIgnoreLoad()) continue; //Ignore test types
            loadFile(mediaType, mediaType.getFileName());
        }
    }

    /**
     * Indl&aelig;ser al data fra en given fil. Medie typen bliver valgt ud fra fil navn
     * @param mediaType = Typen af medie
     * @param filename = Navnet p&aring; filen med dataen (Inkl. fil format)
     * @return Successfuld eller ej
     */
    public boolean loadFile(SupportedMediaTypes mediaType, String filename) {
        //Handles no file exception
        String mediaTypeString = filename.substring(0, filename.indexOf(".")).toUpperCase();

        String basePath = "com/langesokker/data/media/";
        InputStream in = getClass().getClassLoader().getResourceAsStream(basePath + filename);
        if(in == null){
            new ErrorPopup(new JFrame(), "Failed to load", "Failed to load mediatype " + mediaTypeString + " data", true);
            return false;
        }
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String[] arrayCurrentLine = currentLine.split(";");
                Media media = mediaType.toMedia(arrayCurrentLine);
                if(media == null){
                    System.out.println("Failed loading " + arrayCurrentLine[0] + ". Skipping..."); //Not related to user
                    continue;
                }
                /*
                 * Tilføjer genre til arraylist af genre, hvis den ikke eksistere.
                 */
                Arrays.stream(media.getGenres()).forEach(genre -> {
                    if (!knownGenres.contains(genre)) {
                        knownGenres.add(genre);
                    }
                });
                System.out.println(media);
                addMedia(mediaType, media);
            }
            return true;
        } catch (IOException e) {
            new ErrorPopup(new JFrame(), "Failed load", "Failed loading " + mediaTypeString, true);
            e.printStackTrace(); //For console
            return false;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace(); //For console only
                }
            }
        }
    }

    /**
     * Returnere en liste af genre som programmet kender.
     * @return String[]
     */
    public String[] getKnownGenres() {
        return knownGenres.toArray(new String[]{});
    }

    /**
     * @return mediaMap map'en
     */
    public Map<SupportedMediaTypes, List<Media>> getMediaMap() {
        return mediaMap;
    }
}

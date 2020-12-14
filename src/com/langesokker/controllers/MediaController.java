package com.langesokker.controllers;

import com.langesokker.components.ErrorPopup;
import com.langesokker.media.Media;
import com.langesokker.media.SupportedMediaTypes;
import com.langesokker.utils.ImageUtils;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.*;

public class MediaController {
    private static MediaController instance;
    private final Map<SupportedMediaTypes, List<Media>> mediaMap;
    private final List<String> knownGenres;

    public MediaController(){
        this.mediaMap = new HashMap<>();
        this.knownGenres = new ArrayList<>();
        this.knownGenres.add("All genres");
    }

    public static MediaController getInstance() {
        if(instance == null) instance = new MediaController();
        return instance;
    }

    /**
     * Tilfoejer et medie m til listen af film
     * @param media = Det medie man gerne vil tilføje til mappen
     */
    public void addMedia(Media media){
        List<Media> medias;
        if(mediaMap.containsKey(media.getType())){
            medias = mediaMap.get(media.getType());
        }else{
            medias = new ArrayList<>();
        }
        medias.add(media);
        this.mediaMap.put(media.getType(), medias);
    }

    /**
     * Fjerner et medie  fra listen af film
     * @param media = Det medie man gerne vil fjerne fra listen
     */
    public void removeMedia(Media media){
        if(containsMedia(media)) mediaMap.get(media.getType()).remove(media);
    }

    /**
     * Denne methode checker først om typen findes og bagefter går den igennem listen af mediaer.
     * @param media = Det valgte medie man gerne vil finde
     * @return boolean. Medie findes ? TRUE : FALSE;
     */
    public boolean containsMedia(Media media){
        if(!mediaMap.containsKey(media.getType())) return false;
        return mediaMap.get(media.getType()).contains(media);
    }

    /**
     * Brug denne methode til at få det tilhørene billede til mediet, hvis billedet findes.
     * @return = BufferedImage ud fra /data/images/[mediaType]/[mediaNavn].jpg Kan returnere null hvis intet billed findes.
     */
    public BufferedImage getMediaImage(Media media) {
        return ImageUtils.getImage("data/images/" + media.getType().getImageFolderName() + "/" + media.getName() + ".jpg");
    }

    /**
     * Denne funktion går igennem alle filerne i /data/media/ mappen og indlæser dem med MediaController#loadFile(String fileName) funktionen.
     */
    public void loadAllMediaTypes() {
        for(SupportedMediaTypes mediaType : SupportedMediaTypes.values()){
            loadFile(mediaType, mediaType.getFileName());
        }
    }

    /**
     * Indlæser al data fra en given fil. Medie typen bliver valgt ud fra fil navn
     * @param filename = Navnet på filen med dataen (Inkl. fil format)
     */
    public void loadFile(SupportedMediaTypes mediaType, String filename) {
        //Handles no file exception
        String mediaTypeString = filename.substring(0, filename.indexOf(".")).toUpperCase();

        String basePath = "com/langesokker/data/media/";
        InputStream in = getClass().getClassLoader().getResourceAsStream(basePath + filename);
        if(in == null){
            new ErrorPopup(new JFrame(), "Failed to load", "Failed to load mediatype " + mediaTypeString + " data", true);
            return;
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
                addMedia(media);
            }
        } catch (IOException e) {

            new ErrorPopup(new JFrame(), "Failed load", "Failed loading " + mediaTypeString, true);
            e.printStackTrace(); //For console
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

    public String[] getKnownGenres() {
        return knownGenres.toArray(new String[]{});
    }

    public Map<SupportedMediaTypes, List<Media>> getMediaMap() {
        return mediaMap;
    }
}

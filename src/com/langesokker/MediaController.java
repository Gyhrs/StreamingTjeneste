package com.langesokker;

import com.langesokker.media.Media;
import com.langesokker.media.SupportedMediaTypes;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MediaController {
    private static MediaController instance;

    private final Map<SupportedMediaTypes, List<Media>> mediaMap;

    public MediaController(){
        this.mediaMap = new HashMap<>();
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
        List<Media> medias = null;
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
    public BufferedImage getMediaImage(Media media){
        InputStream in = getClass().getResourceAsStream("src/com/langesokker/(data/images/" + media.getType().getImageFolderName() + media.getName() + ".jpg");
        try {
            return ImageIO.read(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Denne funktion går igennem alle filerne i /data/media/ mappen og indlæser dem med MediaController#loadFile(String fileName) funktionen.
     * TODO: Replace new file()
     */
    public void loadAllMediaTypes(){
        File mediaDirectory = new File("src/com/langesokker/data/media");
        if(!mediaDirectory.exists() || !mediaDirectory.isDirectory()){
            System.out.println("Media data folder not found");
            return;
        }
        File[] dataFiles = mediaDirectory.listFiles();
        if(dataFiles == null || dataFiles.length == 0){
            System.out.println("No media data files found");
            return;
        }
        for(File file : dataFiles){
            loadFile(file.getName());
        }
    }

    /**
     * Indlæser al data fra en given fil. Medie typen bliver valgt ud fra fil navn
     * @param filename = Navnet på filen med dataen (Inkl. fil format)
     */
    public void loadFile(String filename) {
        //Handles no file exception
        SupportedMediaTypes mediaType;
        try{
            mediaType = SupportedMediaTypes.valueOf(filename.substring(0, filename.indexOf(".")).toUpperCase());
        }catch (IllegalArgumentException e){
            // TODO: make it throw exception...
            return;
        }
        ClassLoader loader = getClass().getClassLoader();
        if (loader == null) {
            // TODO: make it throw exception...
            return;
        }

        BufferedReader reader = null;
        try {
            String basePath = "src/com/langesokker/data/media/";
            reader = new BufferedReader(new FileReader(basePath + filename));
            String CurrentLine;
            while ((CurrentLine = reader.readLine()) != null) {
                String[] currentLine = CurrentLine.split(";");
                Media media = mediaType.toMedia(currentLine);
                if(media == null){
                    System.out.println("Failed loading " + currentLine[0] + ". Skipping...");
                    continue;
                }
                System.out.println(media.toString());
                addMedia(media);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Map<SupportedMediaTypes, List<Media>> getMediaMap() {
        return mediaMap;
    }
}

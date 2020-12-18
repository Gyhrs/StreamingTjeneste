package com.langesokker.exceptions;

public class ImageNotFoundException extends Exception{

    private final String path;

    /**
     * Konstrukt&oslash;r af ImageNotFoundException
     * @param message = Fejlbeskeden
     * @param path = Stien til billede der ikke kunne indl&aelig;ses
     */
    public ImageNotFoundException(String message, String path){
        super(message);
        this.path = path;
    }

    /**
     * Getter metode til billedestien
     * @return En string med Basepath ("com/langesokker") + localpath
     */
    public String getPath() {
        return path;
    }
}

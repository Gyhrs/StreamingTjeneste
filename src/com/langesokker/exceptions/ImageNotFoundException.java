package com.langesokker.exceptions;

public class ImageNotFoundException extends Exception{

    private final String path;

    public ImageNotFoundException(String message, String path){
        super(message);
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}

package com.langesokker;

public class MediaController {
    private static MediaController instance;

    public static MediaController getInstance() {
        if(instance == null) instance = new MediaController();
        return instance;
    }
    /**
     * Tilføjer valgte film til en liste af film, som streamingtjenesten tilbyder
     */
    public void fillCollection(){


    }

    /**
     * Tilføjer et medie m til listen af film
     */
    public void addMedia(Media m){


    }

    /**
     * Fjerner et medie m fra listen af film
     */
    public void removeMedia(Media m){


    }

}

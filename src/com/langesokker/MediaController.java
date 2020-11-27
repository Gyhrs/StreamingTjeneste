package com.langesokker;

import com.langesokker.media.Media;

public class MediaController {
    private static MediaController instance;

    public static MediaController getInstance() {
        if(instance == null) instance = new MediaController();
        return instance;
    }
    /**
     * Tilfoejer valgte film til en liste af film, som streamingtjenesten tilbyder
     */
    public void fillCollection(){


    }

    /**
     * Tilfoejer et medie m til listen af film
     */
    public void addMedia(){

    }

    /**
     * Fjerner et medie m fra listen af film
     */
    public void removeMedia(){


    }

}

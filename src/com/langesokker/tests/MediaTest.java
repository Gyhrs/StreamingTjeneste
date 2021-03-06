package com.langesokker.tests;

import com.langesokker.controllers.MediaController;
import com.langesokker.media.Media;
import com.langesokker.media.SupportedMediaTypes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MediaTest {

    private MediaController mediaController;

    /**
     * Alt det som skal ske før test
     */
    @Before
    public void before(){
        mediaController = MediaController.getInstance();
    }

    /**
     * Cleanup / Alt det som skal ske efter test
     */
    @After
    public void after(){
        mediaController.getMediaMap().remove(SupportedMediaTypes.TEST);
        mediaController = null;
    }

    /**
     * En test som prøver at loade SupportedMediaTypes.TEST fra test.txt filen.
     * Der bliver tested om loading var successful
     * Om medielisten er null
     * og om størrelsen er som forventet
     */
    @Test
    public void loadFile(){
        boolean successfulLoad = mediaController.loadFile(SupportedMediaTypes.TEST, "test.txt");
        assertTrue(successfulLoad);
        List<Media> mediaList = mediaController.getMediaMap().get(SupportedMediaTypes.TEST);
        assertNotNull(mediaList);
        int expectedSize = 2;
        int size = mediaList.size();
        assertEquals(size, expectedSize);
    }

}

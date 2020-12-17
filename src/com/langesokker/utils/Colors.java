package com.langesokker.utils;

import java.awt.*;

/**
 * Util enum til at holde styr p&aring; hvilke farver der bliver brugt i programmet
 */
public enum Colors {
    PRIMARY_DARK(new Color(31, 31, 31)),
    SECONDARY_DARK(new Color(61,61,61)),
    WHITE(Color.WHITE);

    private final Color color;

    /**
     * Konstrukt&oslash;r
     * @param color = Farven der skal bruges
     */
    Colors(Color color){
        this.color = color;
    }

    /**
     * Getter til farven til v&aelig;rdien
     * @return Color - Farven til v&aelig;rdien
     */
    public Color getColor() {
        return color;
    }
}

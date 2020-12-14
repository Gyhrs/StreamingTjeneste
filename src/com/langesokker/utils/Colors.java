package com.langesokker.utils;

import java.awt.*;

public enum Colors {
    PRIMARY_DARK(new Color(31, 31, 31)),
    SECONDARY_DARK(new Color(61,61,61)),
    WHITE(Color.WHITE);

    private final Color color;

    Colors(Color color){
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}

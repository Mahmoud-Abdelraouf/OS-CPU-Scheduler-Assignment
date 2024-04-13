package com.os.frontend.Colors;

import javafx.scene.paint.Color;

public class Colors {
    private static final Color[] colors = {
            Color.RED,
            Color.ORANGE,
            Color.LIGHTBLUE,
            Color.YELLOW,
            Color.color(0.85, 0.64, 0.58),
            Color.PURPLE,
            Color.CYAN,
            Color.MAGENTA,
            Color.PINK,
            Color.LIME,
            Color.BROWN,
            Color.GRAY,
            Color.BLUE,
            Color.LIGHTGREEN,
            Color.LIGHTPINK,
            Color.LIGHTGRAY,
            Color.DARKRED,
            Color.DARKGREEN,
            Color.DARKBLUE,
            Color.DARKORANGE,
            Color.AQUA,
            Color.BLACK,
            Color.WHITE,
            Color.SILVER,
            Color.GOLD,
            Color.INDIGO,
            Color.TEAL,
            Color.VIOLET,
            Color.YELLOWGREEN,
            Color.CRIMSON,
            Color.LAVENDER,
            Color.SALMON,
            Color.CHOCOLATE,
            Color.TURQUOISE,
            Color.CORAL,
            Color.LIGHTCORAL,
            Color.DARKCYAN,
            Color.LIGHTCYAN,
            Color.DARKVIOLET,
            Color.MEDIUMVIOLETRED,
            Color.LIGHTSALMON,
            Color.LIGHTSTEELBLUE,
            Color.DARKGOLDENROD,
            Color.DARKKHAKI,
            Color.LIGHTSEAGREEN,
            // Add more colors as needed
    };

    public static String getColor(int index) {
        if (index >= 0 && index < colors.length) {
            Color color = colors[index];
            return toRGBCode(color);
        }
        return null;
    }


    // Helper method to convert Color object to RGB code
    private static String toRGBCode(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }
}



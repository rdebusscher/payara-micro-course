package be.rubus.courses.payara.micro.jpa.converter;

import java.awt.*;

/**
 * Converter for java.awt.Color. Is used by JPA and JAX-RS converter.
 */
public class ColorConverter {

    public String convertToString(Color color) {
        if (color == null) {
            return "";
        }
        return color.getRed() +
                ":" +
                color.getGreen() +
                ":" +
                color.getBlue();
    }

    public Color convertFromString(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        String[] parts = value.split(":");

        return new Color(Integer.parseInt(parts[0])
                , Integer.parseInt(parts[1])
                , Integer.parseInt(parts[2]));
    }
}

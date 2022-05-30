package be.rubus.courses.payara.micro.jpa.converter;

import javax.json.bind.serializer.DeserializationContext;
import javax.json.bind.serializer.JsonbDeserializer;
import javax.json.stream.JsonParser;
import java.awt.*;
import java.lang.reflect.Type;

public class ColorDeserializer implements JsonbDeserializer<Color> {

    private final ColorConverter converter = new ColorConverter();

    @Override
    public Color deserialize(JsonParser parser, DeserializationContext ctx, Type rtType) {
        String colorString = parser.getString();
        return converter.convertFromString(colorString);
    }
}

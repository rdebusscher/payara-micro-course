package be.rubus.courses.payara.micro.jpa.converter;

import javax.json.bind.serializer.JsonbSerializer;
import javax.json.bind.serializer.SerializationContext;
import javax.json.stream.JsonGenerator;
import java.awt.*;

public class ColorSerializer implements JsonbSerializer<Color> {

    private final ColorConverter converter = new ColorConverter();

    @Override
    public void serialize(Color obj, JsonGenerator generator, SerializationContext ctx) {
        generator.write(converter.convertToString(obj));
    }
}

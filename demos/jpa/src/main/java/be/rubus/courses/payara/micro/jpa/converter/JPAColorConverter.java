package be.rubus.courses.payara.micro.jpa.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.awt.*;

@Converter
public class JPAColorConverter extends ColorConverter implements AttributeConverter<Color, String> {
    @Override
    public String convertToDatabaseColumn(Color attribute) {
        return convertToString(attribute);
    }

    @Override
    public Color convertToEntityAttribute(String dbData) {
        return convertFromString(dbData);
    }
}

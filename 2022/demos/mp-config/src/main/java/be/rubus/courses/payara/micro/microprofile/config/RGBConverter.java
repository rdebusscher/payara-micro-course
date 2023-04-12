package be.rubus.courses.payara.micro.microprofile.config;

import org.eclipse.microprofile.config.spi.Converter;

public class RGBConverter implements Converter<RGB> {
    @Override
    public RGB convert(String s) {
        String[] parts = s.split(",");
        return new RGB(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
    }
}

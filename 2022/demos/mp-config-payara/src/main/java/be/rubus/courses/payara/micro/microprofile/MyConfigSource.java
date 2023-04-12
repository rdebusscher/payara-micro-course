package be.rubus.courses.payara.micro.microprofile;

import org.eclipse.microprofile.config.spi.ConfigSource;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

/**
 * A config source that Produces dynamic values. It needs to be registered through the `ServiceLoader` pattern.
 */
public class MyConfigSource implements ConfigSource {
    @Override
    public Set<String> getPropertyNames() {
        return Set.of("now");
    }

    @Override
    public String getValue(String propertyName) {
        if ("now".equals(propertyName)) {
            SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss");
            return fmt.format(new Date());
        }
        return null;
    }

    @Override
    public String getName() {
        return "DateProvidingConfigSource";
    }
}

package be.rubus.courses.payara.micro.cdi;

import be.rubus.courses.payara.micro.cdi.producer.NumberLogger;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.math.BigDecimal;

public class App3 {

    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            // You can define a producer for an instance when a common type instance is needed (String, numbers, ...)
            // initialization is complex or when we want to have a customized version depending on where we inject it.
            BigDecimal value = container.select(BigDecimal.class).get();

            // Within NumberLogger we use a producer to get a customized JUL logger.
            NumberLogger logger = container.select(NumberLogger.class).get();
            logger.log(value);

            BigDecimal value2 = container.select(BigDecimal.class).get();

            logger.log(value2);
        }
    }
}

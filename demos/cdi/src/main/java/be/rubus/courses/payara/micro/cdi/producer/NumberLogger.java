package be.rubus.courses.payara.micro.cdi.producer;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

@Dependent
public class NumberLogger {

    private final Logger logger;

    // Constructor injection instead of field injection.
    @Inject
    public NumberLogger(Logger logger) {
        this.logger = logger;
    }

    public void log(BigDecimal value) {
        logger.log(Level.INFO, "The BigDecimal value is {0}", value);
    }


}

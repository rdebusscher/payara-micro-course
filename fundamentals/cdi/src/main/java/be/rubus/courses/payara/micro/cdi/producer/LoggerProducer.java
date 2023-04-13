package be.rubus.courses.payara.micro.cdi.producer;


import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
class LoggerProducer {

    private static final Logger LOGGER = Logger.getLogger(LoggerProducer.class.getName());

    @Produces
    Logger getLog(InjectionPoint ip) {
        // Inspect the Injection point to make
        String declaringClass = ip.getMember().getDeclaringClass().getName();
        LOGGER.log(Level.INFO, "Creating instance log for class {0}", declaringClass);
        return Logger.getLogger(declaringClass);
    }
}

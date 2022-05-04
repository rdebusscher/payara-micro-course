package be.rubus.courses.payara.micro.cdi.news;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.util.logging.Logger;

@ApplicationScoped
public class SpecificMedia {

    private static final Logger LOGGER = Logger.getLogger(SpecificMedia.class.getName());

    public void accept(@Observes @Specific News news) {
        LOGGER.info("It will only listen to the specific news items: " + news.getTopic());
    }
}

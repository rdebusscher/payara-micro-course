package be.rubus.courses.payara.micro.cdi.news;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class Magazine {

    private static final Logger LOGGER = Logger.getLogger(Magazine.class.getName());

    public void accept(@Observes News news) {
        LOGGER.log(Level.INFO, "We got the news, we''ll publish it on a magazine: {0}", news.getTopic());
    }
}

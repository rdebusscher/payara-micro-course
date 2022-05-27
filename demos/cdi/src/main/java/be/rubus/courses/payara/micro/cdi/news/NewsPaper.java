package be.rubus.courses.payara.micro.cdi.news;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class NewsPaper {

    private static final Logger LOGGER = Logger.getLogger(NewsPaper.class.getName());

    public void accept(@Observes News news) {
        LOGGER.log(Level.INFO, "We got the news, we''ll publish it on a newspaper: {0}", news.getTopic());
    }
}

package be.rubus.courses.payara.micro.cdi.news;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class SocialMedia  {

    private static final Logger LOGGER = Logger.getLogger(SocialMedia.class.getName());

    public void accept(@Observes News news) {
        LOGGER.log(Level.INFO, "We got the news, we''ll publish it on Social Media: {0}" , news.getTopic());
    }
}

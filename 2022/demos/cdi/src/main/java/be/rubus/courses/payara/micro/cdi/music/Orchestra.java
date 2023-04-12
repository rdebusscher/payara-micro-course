package be.rubus.courses.payara.micro.cdi.music;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@ApplicationScoped
public class Orchestra {

    private static final Logger LOGGER = Logger.getLogger(Orchestra.class.getName());

    @Inject
    @MusicalInstrument(InstrumentType.PERCUSSION)
    private Instrument percussion;
    @Inject
    @MusicalInstrument(InstrumentType.KEYBOARD)
    private Instrument keyboard;

    @Inject
    @MusicalInstrument(InstrumentType.STRING)
    private Instrument string;

    @Inject  // No qualifier, so @Default is taken = Piano
    private Instrument solo;

    @Inject
    @Any
    // Instance can be used when optional or multiple matches.
    private Instance<Instrument> instruments;

    public void string() {
        LOGGER.log(Level.INFO, "The string sound: {0}", string.sound());
    }

    public void percussion() {
        LOGGER.log(Level.INFO, "The percussion sound: {0}", percussion.sound());
    }

    public void keyboard() {
        LOGGER.log(Level.INFO, "The keyboard''s sound: {0}", keyboard.sound());
    }


    public void solo() {
        LOGGER.log(Level.INFO, "The solo sound: {0}", solo.sound());
    }

    public void allSound() {
        String sounds = this.instruments.stream().map(Instrument::sound).collect(Collectors.joining(", "));
        LOGGER.log(Level.INFO, "All instruments sounds are: {0}", sounds);
    }
}

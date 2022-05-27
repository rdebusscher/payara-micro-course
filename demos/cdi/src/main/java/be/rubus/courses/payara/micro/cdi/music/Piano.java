package be.rubus.courses.payara.micro.cdi.music;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;

@MusicalInstrument(InstrumentType.KEYBOARD)
@Default  // Special annotation to indicate this is the default one when no qualifier is specified
@ApplicationScoped
class Piano implements Instrument {
    @Override
    public String sound() {
        return "piano";
    }
}

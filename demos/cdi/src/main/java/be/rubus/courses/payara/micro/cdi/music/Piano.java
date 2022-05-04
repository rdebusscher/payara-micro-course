package be.rubus.courses.payara.micro.cdi.music;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;

@MusicalInstrument(InstrumentType.KEYBOARD)
@Default
@ApplicationScoped
class Piano implements Instrument {
    @Override
    public String sound() {
        return "piano";
    }
}

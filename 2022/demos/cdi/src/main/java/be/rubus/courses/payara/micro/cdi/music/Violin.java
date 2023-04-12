package be.rubus.courses.payara.micro.cdi.music;

import javax.enterprise.context.ApplicationScoped;

@MusicalInstrument(InstrumentType.STRING)
@ApplicationScoped
class Violin implements Instrument {
    @Override
    public String sound() {
        return "violin";
    }
}

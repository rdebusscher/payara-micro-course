package be.rubus.courses.payara.micro.cdi.music;

import javax.enterprise.context.ApplicationScoped;

@MusicalInstrument(InstrumentType.PERCUSSION)
@ApplicationScoped
class Xylophone implements Instrument {
    @Override
    public String sound() {
        return "xylophone";
    }
}

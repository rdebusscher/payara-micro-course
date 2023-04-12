package be.rubus.courses.payara.micro.cdi.audited;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@ApplicationScoped
public class SlowSupplier implements Supplier<String> {
// Supplier has nothing to do with the interceptor, just for convenience of the example

    @Timed
    @Override
    public String get() {
        try {
            TimeUnit.MILLISECONDS.sleep(200L);
        } catch (InterruptedException e) {
            //TODO it is only a sample, don't do it on production :)
            throw  new RuntimeException(e);
        }
        return "The slow result";
    }
}

package be.rubus.courses.payara.micro.cdi.audited;

import javax.enterprise.context.ApplicationScoped;
import java.util.function.Supplier;

@ApplicationScoped
public class FastSupplier implements Supplier<String> {
    // Supplier has nothing to do with the interceptor, just for convenience of the example

    @Timed
    @Override
    public String get() {
        return "The Fast supplier result";
    }
}

package be.rubus.courses.payara.micro.cdi.producer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

@ApplicationScoped  // Defines th scope of the Producer Bean (= also a CDI bean)
class NumberProducer {

    @Produces  // Always produces a bean with type @Dependant.
    public BigDecimal producer() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        double nextDouble = random.nextInt(1, 100);
        return new BigDecimal(nextDouble);
    }

    public void destroy(@Disposes BigDecimal value) {
        System.out.println("We don't need this number anymore: " + value);
    }
}

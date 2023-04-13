package be.rubus.courses.payara.micro.cdi.vehicle;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.Objects;
import java.util.UUID;
import java.util.logging.Logger;

@ApplicationScoped
public class Car implements Vehicle {

    private static final Logger LOGGER = Logger.getLogger(Car.class.getName());

    private String name;

    public Car() {
        // don't do any initializing.
    }

    @PostConstruct
    public void init() {
        // Initialization of the bean should be done in the @PostConstruct annotated method
        // and not thought the constructor.
        name = UUID.randomUUID().toString();
        LOGGER.info("The Vehicle CDI bean with name " + name + " is ready now in the container");
        // actually not ready at this point but just after this method returns :)
    }

    @Override
    public void move() {
        LOGGER.info("My car is moving. The car's name is: " + name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return Objects.equals(name, car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}

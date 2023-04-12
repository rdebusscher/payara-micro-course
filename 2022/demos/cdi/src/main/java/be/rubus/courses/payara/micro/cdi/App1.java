package be.rubus.courses.payara.micro.cdi;

import be.rubus.courses.payara.micro.cdi.vehicle.Car;
import be.rubus.courses.payara.micro.cdi.vehicle.Vehicle;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import javax.enterprise.inject.spi.CDI;

public class App1 {

    public static void main(String[] args) {
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

            // This is how you programmatically select a CDI bean from the container.
            // This fails when multiple beans match the selected criteria.
            Vehicle vehicle = container.select(Vehicle.class).get();
            vehicle.move();

            // Within a Runtime, the `CDI` is the way to go as you don't have access to the container.
            Car car = CDI.current().select(Car.class).get();
            car.move();

            System.out.printf("Is the same vehicle? %s %n", car.equals(vehicle));
            System.out.printf("Is it the same reference? %s %n", car == vehicle);

            // A CDI instance is characterised by its class, super classes, or interfaces.
            // ApplicationScoped means there is only 1 instance for the entire 'application' (context)
        }
    }
}

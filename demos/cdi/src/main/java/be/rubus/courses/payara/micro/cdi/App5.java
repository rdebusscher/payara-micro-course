package be.rubus.courses.payara.micro.cdi;

import be.rubus.courses.payara.micro.cdi.decorator.Worker;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

public class App5 {

    public static void main(String[] args) {
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            // This receives an instance of the decorator
            Worker worker = container.select(Worker.class).get();
            String work = worker.work("Just a single button");
            System.out.println("The work result: " + work);
            System.out.println("actual class: " + worker.getClass().getName());
        }
    }
}

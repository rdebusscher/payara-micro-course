package be.rubus.courses.payara.micro.cdi;

import be.rubus.courses.payara.micro.cdi.music.Orchestra;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

public class App2 {

    public static void main(String[] args) {
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            Orchestra orchestra = container.select(Orchestra.class).get();
            // All 'dependencies' are injected.  Distinction is made based on the Qualifier if
            // multiple beans can be matched.
            orchestra.percussion();
            orchestra.keyboard();
            orchestra.string();
            orchestra.solo();
            orchestra.allSound();

        }
    }
}

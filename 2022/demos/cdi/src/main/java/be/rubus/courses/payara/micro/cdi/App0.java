package be.rubus.courses.payara.micro.cdi;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import java.util.Set;

public class App0 {

    public static void main(String[] args) {
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            // Container started and (programmatic) access to all beans managed by the container.
            BeanManager manager = container.getBeanManager();
            Set<Bean<?>> beans = manager.getBeans(Object.class);
            for (Bean<?> bean : beans) {
                System.out.println(bean);
            }

            System.out.printf("Beans count: %s %n", beans.size());
            // Many built in beans but also all 'beans' (POJO) within project as it performs scanning
            // of the classpath during startup.
            // beans.xml to configure the CDI system, including limiting the POJOs that are considered as CDI beans.
        }
    }
}

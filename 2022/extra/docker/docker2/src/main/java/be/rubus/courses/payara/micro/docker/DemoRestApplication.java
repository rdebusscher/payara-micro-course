package be.rubus.courses.payara.micro.docker;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 */
@ApplicationPath("/data")
public class DemoRestApplication extends Application {
}

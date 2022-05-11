package be.rubus.courses.payara.micro.microprofile.config;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/config-sources")
@ApplicationScoped
public class E1Resource {

    @Inject
    @ConfigProperty(name = "config.key", defaultValue = "From Code")
    private String configValue;

    @GET
    public String getInjectedConfigValue() {
        return "Config value : " + configValue;
    }
}

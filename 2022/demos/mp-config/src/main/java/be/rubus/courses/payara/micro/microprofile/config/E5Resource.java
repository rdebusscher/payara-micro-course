package be.rubus.courses.payara.micro.microprofile.config;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/config/variables")
@RequestScoped
public class E5Resource {

    @Inject
    @ConfigProperty(name = "complex-value")
    private String value;

    @GET
    public String getInjectedConfigValue() {
        return "Result of resolved value :  " + value;
    }

}

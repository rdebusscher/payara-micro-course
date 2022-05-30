package be.rubus.courses.payara.micro.microprofile;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/config")
@RequestScoped
public class TestResource {

    @Inject
    @ConfigProperty(name = "now")
    private String nowInject;

    @GET
    public String getInjectedConfigValue() {
        return String.format("Current time %s ", nowInject);
    }

}

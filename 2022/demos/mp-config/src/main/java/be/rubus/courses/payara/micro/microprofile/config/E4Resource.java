package be.rubus.courses.payara.micro.microprofile.config;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/config/provider")
@RequestScoped
public class E4Resource {


    @Inject
    @ConfigProperty(name = "injected.value")
    private Provider<String> justInTimeValue;

    @GET
    public String getConfigValue() {
        return justInTimeValue.get();
    }

}

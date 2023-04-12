package be.rubus.courses.payara.micro.microprofile.config;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.Optional;

@Path("/config/optional")
@RequestScoped
public class E2Resource {

    @Inject
    @ConfigProperty(name = "optional.value")
    private Optional<String> optionalValue;

    @GET
    public String getInjectedConfigValue() {
        return "Optional Config value :" + optionalValue.orElse("No Value Defined");
    }

}

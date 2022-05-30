package be.rubus.courses.payara.micro.microprofile.config;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@Path("/config/converter")
@RequestScoped
public class E3Resource {

    @Inject
    @ConfigProperty(name = "myPets")
    private List<String> pets;

    @Inject
    @ConfigProperty(name = "color")
    private RGB rgbValue;

    @GET
    public String getConfigValue() {
        String myPets = String.join(" - ", pets);
        return String.format("Pets : %s, Color :  %s", myPets, rgbValue);
    }

}

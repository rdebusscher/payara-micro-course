package be.rubus.courses.payara.micro.jaxrs.filter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

@Path("/secure")
public class E7SecureResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String doGreeting(@Context SecurityContext secure) {
        return "From the secured area, Hello to " + secure.getUserPrincipal().getName();
    }

}

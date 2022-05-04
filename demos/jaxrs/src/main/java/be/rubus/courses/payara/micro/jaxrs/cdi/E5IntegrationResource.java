package be.rubus.courses.payara.micro.jaxrs.cdi;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/cdi")
@Singleton  // Only 1 instance, optimal memory pressure (when not 'injecting' URL parameters in fields)
public class E5IntegrationResource {

    @Inject
    private MyService myService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{name}")
    public String doGreeting(@PathParam("name") String someValue, @QueryParam("language") String language) {
        return myService.getGreeting(someValue, language);
    }
}

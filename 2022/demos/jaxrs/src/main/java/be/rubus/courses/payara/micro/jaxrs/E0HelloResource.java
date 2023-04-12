package be.rubus.courses.payara.micro.jaxrs;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class E0HelloResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHello() {
        return "Hello World";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{name}")
    public String doGreeting(@PathParam("name") String someValue, @QueryParam("language") String language) {
        return "Hello " + someValue + " with language " + language;
    }
}

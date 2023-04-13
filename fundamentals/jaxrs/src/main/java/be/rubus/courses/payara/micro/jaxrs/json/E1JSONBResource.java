package be.rubus.courses.payara.micro.jaxrs.json;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/person")
public class E1JSONBResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Person getPerson() {
        return new Person("Rudy", 42);
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String handlePersonRequest(Person person) {
        return person.toString();
    }
}

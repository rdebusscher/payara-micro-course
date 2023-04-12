package be.rubus.courses.payara.micro.jaxrs.response;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.Collections;

@Path("/evenValue")
public class E3ResponseResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{intVal}")
    public Response testValue(@PathParam("intVal") Integer value) {
        if (value % 2 == 0) {
           return Response.ok("Value is a correct even number") .build();
        } else {
            return Response.notAcceptable(Collections.emptyList()).build();
        }
    }
}

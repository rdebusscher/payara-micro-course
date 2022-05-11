package be.rubus.courses.payara.micro.lab.advanced.shop.resource;

import be.rubus.courses.payara.micro.lab.advanced.shop.ErrorMessage;
import be.rubus.courses.payara.micro.lab.advanced.shop.model.Credentials;
import be.rubus.courses.payara.micro.lab.advanced.shop.service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/userAdmin")
@Singleton
@Produces(MediaType.APPLICATION_JSON)
public class UserAdminResource {

    @Inject
    private UserService userService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(Credentials credentials) {
        ErrorMessage errorMessage = userService.addUser(credentials);
        if (errorMessage != null) {
            // User could not be created (already existing for example), return status 412 with the message.
            return Response.status(Response.Status.PRECONDITION_FAILED).entity(errorMessage).build();
        }
        return Response.status(Response.Status.CREATED).build();
    }
}

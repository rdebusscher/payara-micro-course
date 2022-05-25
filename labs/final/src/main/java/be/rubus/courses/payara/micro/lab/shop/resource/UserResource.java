package be.rubus.courses.payara.micro.lab.shop.resource;

import be.rubus.courses.payara.micro.lab.shop.model.User;
import be.rubus.courses.payara.micro.lab.shop.service.UserService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


/**
 * JAX-RS resource for getting user information. Since we have a fixed user, you can use this to test out basic setup.
 */
@Path("/user")
@Singleton
public class UserResource {

    @Inject
    private UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser() {
        return userService.getUser();
    }
}

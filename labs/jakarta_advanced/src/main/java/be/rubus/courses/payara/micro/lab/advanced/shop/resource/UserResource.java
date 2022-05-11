package be.rubus.courses.payara.micro.lab.advanced.shop.resource;

import be.rubus.courses.payara.micro.lab.advanced.shop.model.User;
import be.rubus.courses.payara.micro.lab.advanced.shop.service.UserService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;


/**
 * JAX-RS resource for getting user information. Since this is secure it can be used to test the Authentication filter.
 */
@Path("/secure/user")
@Singleton
public class UserResource {

    @Inject
    private UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@Context SecurityContext securityContext) {
        return userService.findUserByUserName(securityContext.getUserPrincipal().getName()).orElse(null);
    }
}

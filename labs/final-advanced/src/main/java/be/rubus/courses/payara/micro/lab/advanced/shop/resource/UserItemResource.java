package be.rubus.courses.payara.micro.lab.advanced.shop.resource;

import be.rubus.courses.payara.micro.lab.advanced.shop.ErrorMessage;
import be.rubus.courses.payara.micro.lab.advanced.shop.model.PaymentType;
import be.rubus.courses.payara.micro.lab.advanced.shop.model.Product;
import be.rubus.courses.payara.micro.lab.advanced.shop.model.User;
import be.rubus.courses.payara.micro.lab.advanced.shop.service.UserItemService;
import be.rubus.courses.payara.micro.lab.advanced.shop.service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

/**
 * Secure endpoints to manage the products of a User.
 */
@Path("/secure/user/items")
@RequestScoped  // Or inject Security Context as parameter on each method.
@Produces(MediaType.APPLICATION_JSON)
public class UserItemResource {

    @Context
    private SecurityContext securityContext;

    @Inject
    private UserService userService;

    @Inject
    private UserItemService userItemService;

    @GET
    public List<Product> retrieveUserItems() {
        User user = userService.findUserByUserName(securityContext.getUserPrincipal().getName()).orElse(null);
        // Should never be null (we can't delete user in database in this lab)

        return userItemService.productsOfUser(user);
        // Automatic conversion to JSON with status 200
    }

    @POST
    public Response addUserItem(@QueryParam("productId") List<String> productIds, @QueryParam("payment") @NotNull PaymentType paymentType) {
        User user = userService.findUserByUserName(securityContext.getUserPrincipal().getName()).orElse(null);
        // Should never be null (we can't delete user in database in this lab)

        if (productIds.isEmpty()) {
            return Response.status(Response.Status.PRECONDITION_FAILED).entity(new ErrorMessage("AD01", "missing productId query parameter")).build();
        }
        ErrorMessage errorMessage = userItemService.addProducts(user, productIds, paymentType);
        if (errorMessage != null) {
            // Business validation, can also be handled with Exceptions and a ExceptionMapper.
            return Response.status(Response.Status.PRECONDITION_FAILED).entity(errorMessage).build();
        }
        // Response.created expects a URI to access the newly added info. We do not support this.
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    public Response removeUserItem(@QueryParam("productId") List<String> productIds) {

        User user = userService.findUserByUserName(securityContext.getUserPrincipal().getName()).orElse(null);
        // Should never be null (we can't delete user in database in this lab)

        if (productIds.isEmpty()) {
            return Response.status(Response.Status.PRECONDITION_FAILED).entity(new ErrorMessage("AD01", "missing productId query parameter")).build();
        }
        ErrorMessage errorMessage = userItemService.removeProducts(user, productIds);

        if (errorMessage != null) {
            return Response.status(Response.Status.PRECONDITION_FAILED).entity(errorMessage).build();
        }
        // Response.created expects a URI to access the newly added info. We d not support this.
        return Response.status(Response.Status.OK).build();

    }
}
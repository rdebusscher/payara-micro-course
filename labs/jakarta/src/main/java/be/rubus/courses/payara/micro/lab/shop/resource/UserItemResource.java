package be.rubus.courses.payara.micro.lab.shop.resource;

import be.rubus.courses.payara.micro.lab.shop.ErrorMessage;
import be.rubus.courses.payara.micro.lab.shop.model.Product;
import be.rubus.courses.payara.micro.lab.shop.service.UserItemService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Secure endpoints to manage the products of a User.
 */
@Path("/user/items")
@Singleton
@Produces(MediaType.APPLICATION_JSON)
public class UserItemResource {

    @Inject
    private UserItemService userItemService;

    @GET
    public List<Product> retrieveUserItems() {
        return userItemService.productsOfUser();
        // Automatic conversion to JSON with status 200
    }

    @POST
    public Response addUserItem(@QueryParam("productId") List<String> productIds) {
        if (productIds.isEmpty()) {
            return Response.status(Response.Status.PRECONDITION_FAILED).entity(new ErrorMessage("AD01", "missing productId query parameter")).build();
        }
        ErrorMessage errorMessage = userItemService.addProducts(productIds);
        if (errorMessage != null) {
            // Business validation, can also be handled with Exceptions and a ExceptionMapper.
            return Response.status(Response.Status.PRECONDITION_FAILED).entity(errorMessage).build();
        }
        // Response.created expects a URI to access the newly added info. We do not support this.
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    public Response removeUserItem(@QueryParam("productId") List<String> productIds) {

        if (productIds.isEmpty()) {
            return Response.status(Response.Status.PRECONDITION_FAILED).entity(new ErrorMessage("AD01", "missing productId query parameter")).build();
        }
        ErrorMessage errorMessage = userItemService.removeProducts(productIds);

        if (errorMessage != null) {
            return Response.status(Response.Status.PRECONDITION_FAILED).entity(errorMessage).build();
        }
        // Response.created expects a URI to access the newly added info. We d not support this.
        return Response.status(Response.Status.OK).build();

    }
}
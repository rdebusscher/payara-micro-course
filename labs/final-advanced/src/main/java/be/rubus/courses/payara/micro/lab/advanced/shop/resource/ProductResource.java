package be.rubus.courses.payara.micro.lab.advanced.shop.resource;

import be.rubus.courses.payara.micro.lab.advanced.shop.ErrorMessage;
import be.rubus.courses.payara.micro.lab.advanced.shop.model.ProductCategory;
import be.rubus.courses.payara.micro.lab.advanced.shop.service.ProductService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * Endpoint to manage the products. We support just read in this example.
 */
@Path("/product")
@Singleton
public class ProductResource {

    @Inject
    private ProductService productService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts(@QueryParam("category") ProductCategory productCategory
            , @QueryParam("minPrice") Double minPrice
            , @QueryParam("maxPrice") Double maxPrice) {
        if (minPrice != null && maxPrice != null) {
            if (minPrice > maxPrice) {
                // Status 412 when minPrice is not smaller than maxPrice.
                ErrorMessage errorMessage = new ErrorMessage("AD04","'minPrice' must be smaller than 'maxPrice' value.");
                return Response.status(Response.Status.PRECONDITION_FAILED).entity(errorMessage).build();
            }
        }
        return Response.ok().entity(productService.findProducts(productCategory, minPrice, maxPrice)).build();
    }
}

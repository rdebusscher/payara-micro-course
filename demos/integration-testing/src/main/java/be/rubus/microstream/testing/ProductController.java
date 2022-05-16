package be.rubus.microstream.testing;


import be.rubus.microstream.testing.model.Product;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

@ApplicationScoped
@Path("/products")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductController {
    @Inject
    private ProductRepository repository;


    @GET
    public Collection<Product> getAll() {
        return this.repository.getAll();
    }

    @GET
    @Path("{id}")
    public Product findById(@PathParam("id") long id) {
        return repository.findById(id).orElseThrow(
                () -> new WebApplicationException("There is no product with the id " + id, Response.Status.NOT_FOUND));
    }

    @POST
    public Response insert( Product product) {
        return Response.status(Response.Status.CREATED).entity(repository.save(product)).build();
    }

    @DELETE
    @Path("{id}")
	public Response delete(
             @PathParam("id")  long id) {
		this.repository.deleteById(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}

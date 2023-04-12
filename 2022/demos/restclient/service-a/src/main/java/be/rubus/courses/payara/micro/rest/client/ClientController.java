package be.rubus.courses.payara.micro.rest.client;

import org.eclipse.microprofile.faulttolerance.*;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;


@Path("/client")
@ApplicationScoped
public class ClientController {

    @Inject
    @RestClient
    private Service service;

    @GET
    @Path("/test/{parameter}")
    @Operation(
            summary = ""
            ,description = ""

    )
    @Bulkhead()
    @Timeout
    public String onClientSide( @PathParam("parameter") String parameter) {

        return service.doSomething(parameter);
    }

    public String alternativeValue(String parameter) {
        return "No data";
    }
}
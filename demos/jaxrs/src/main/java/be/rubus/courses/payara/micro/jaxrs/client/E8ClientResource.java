package be.rubus.courses.payara.micro.jaxrs.client;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Path("/client")
@Singleton
public class E8ClientResource {

    private Client client;

    @PostConstruct
    public void init() {
        // See also for MicroProfile Client for a high level solution.
        client = ClientBuilder.newClient();
        // Creation of Client is expensive but Thread safe, so do it once.
    }

    @GET
    @Path("/{name}")
    public String usingClient(@PathParam("name") String userName, @Context UriInfo uriInfo) {

        String authorizationBasicValue = userName + ":mySecretPassword";
        String encoded = Base64.getEncoder().encodeToString(authorizationBasicValue.getBytes(StandardCharsets.UTF_8));
        Response response = client.target(uriInfo.getBaseUri())
                .path("/secure")
                .request()
                .header("Authorization", "BASIC " + encoded)
                .get();

        return response.readEntity(String.class);
    }
}

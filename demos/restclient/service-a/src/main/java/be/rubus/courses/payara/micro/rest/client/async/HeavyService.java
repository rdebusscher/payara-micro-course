package be.rubus.courses.payara.micro.rest.client.async;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.concurrent.CompletionStage;

@RegisterRestClient
@ApplicationScoped
public interface HeavyService {

    @GET
    CompletionStage<String> calculate();
    // Returning CompletionStage makes it possible to make an async call of the remote service.
}

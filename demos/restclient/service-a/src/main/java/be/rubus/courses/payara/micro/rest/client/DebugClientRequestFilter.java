package be.rubus.courses.payara.micro.rest.client;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import java.io.IOException;

public class DebugClientRequestFilter implements ClientRequestFilter {
    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        // We can modify the client request in this filter.
        System.out.println("Remote Service URL : " + requestContext.getUri());
    }
}
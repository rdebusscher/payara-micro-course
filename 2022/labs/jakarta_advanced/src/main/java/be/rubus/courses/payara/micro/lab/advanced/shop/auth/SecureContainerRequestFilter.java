package be.rubus.courses.payara.micro.lab.advanced.shop.auth;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import java.io.IOException;

/**
 * A Filter that is applied to all JAX-RS calls to see if authentication is provided for the secure endpoints.
 */
public class SecureContainerRequestFilter implements ContainerRequestFilter {

    public static final String BASIC_AUTHORIZATION_MARKER = "BASIC ";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

    }


}

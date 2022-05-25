package be.rubus.courses.payara.micro.lab.advanced.shop.auth;

import be.rubus.courses.payara.micro.lab.advanced.shop.model.User;
import be.rubus.courses.payara.micro.lab.advanced.shop.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * A Filter that is applied to all JAX-RS calls to see if authentication is provided for the secure endpoints.
 */
@Provider
public class SecureContainerRequestFilter implements ContainerRequestFilter {

    public static final String BASIC_AUTHORIZATION_MARKER = "BASIC ";
    @Inject
    private UserService userService;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String path = requestContext.getUriInfo().getRequestUri().getPath();
        boolean allowAccess = true;  // For the moment, user has access.
        if (path.contains("/secure")) {
            // secure endpoint?
            String authorization = requestContext.getHeaderString("Authorization");
            if (isBasic(authorization)) {
                // We have a Header with Basic Authentication, is it a valid user?
                User user = validateUser(authorization);
                if (user != null) {
                    requestContext.setSecurityContext(new SimpleSecurityContext(user));
                } else {
                    allowAccess = false;
                }
            } else {
                allowAccess = false;
            }

        }
        if (!allowAccess) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    /**
     * When valid credentials specified, it returns the {@link User}. null when credentials are not valid.
     * @param authorization  The Authorization Header value.
     * @return The User matching the Authorization info or null when invalid.
     */
    private User validateUser(String authorization) {
        User result = null;
        String encoded = authorization.substring(BASIC_AUTHORIZATION_MARKER.length());  // Remove BAsic at the front.
        //Perform a Base64 decode
        String credentials = new String(Base64.getDecoder().decode(encoded), StandardCharsets.UTF_8);
        // username:password format
        String[] parts = credentials.split(":");
        if (parts.length == 2) {
            // Validate the credentials.
            result = isValid(parts[0], parts[1]);
        }
        return result;
    }

    private User isValid(String userName, String password) {
        // See if User exists, we do not check the password.
        if (!userName.isEmpty() && !password.isEmpty()) {
            return userService.findUserByUserName(userName).orElse(null);
        }
        return null;
    }

    private boolean isBasic(String authorization) {
        // Check if authorization header specified and is BASIC authentication.
        return authorization != null && authorization.startsWith(BASIC_AUTHORIZATION_MARKER);
    }
}

package be.rubus.courses.payara.micro.jaxrs.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Provider
public class SecureContainerRequestFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String path = requestContext.getUriInfo().getRequestUri().getPath();
        boolean allowAccess = true;
        // There are better ways to check, also have a look at MicroProfile JWT Auth.
        if (path.contains("/secure")) {
            String authorization = requestContext.getHeaderString("Authorization");
            if (isBasic(authorization)) {
                String user = validateUser(authorization);
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

    private String validateUser(String authorization) {
        String result = null;
        String encoded = authorization.substring(6);
        String credentials = new String(Base64.getDecoder().decode(encoded), StandardCharsets.UTF_8);
        String[] parts = credentials.split(":");
        if (parts.length == 2) {
            if (isValid(parts[0], parts[1])) {
                result = parts[0];
            }
        }
        return result;
    }

    private boolean isValid(String userName, String password) {
        // We need to have a proper check on these of course :)
        return !userName.isEmpty() && !password.isEmpty();
    }

    private boolean isBasic(String authorization) {
        return authorization != null && authorization.startsWith("BASIC ");
    }
}

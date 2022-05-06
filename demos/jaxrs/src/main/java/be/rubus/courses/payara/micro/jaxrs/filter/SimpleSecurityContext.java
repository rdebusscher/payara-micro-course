package be.rubus.courses.payara.micro.jaxrs.filter;

import javax.security.enterprise.CallerPrincipal;
import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

public class SimpleSecurityContext implements SecurityContext {

    private final CallerPrincipal principal;

    public SimpleSecurityContext(String user) {
        principal = new CallerPrincipal(user);
    }

    @Override
    public Principal getUserPrincipal() {
        return principal;
    }

    @Override
    public boolean isUserInRole(String role) {
        return true;  // We don't have roles in this simple Security Context
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public String getAuthenticationScheme() {
        return SecurityContext.BASIC_AUTH;
    }
}

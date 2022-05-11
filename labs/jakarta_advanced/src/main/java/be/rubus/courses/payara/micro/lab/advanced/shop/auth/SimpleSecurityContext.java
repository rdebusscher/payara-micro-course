package be.rubus.courses.payara.micro.lab.advanced.shop.auth;

import be.rubus.courses.payara.micro.lab.advanced.shop.model.User;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

/**
 * A simple {@link SecurityContext} that holds a Principal based on our {@link User} model.
 */
public class SimpleSecurityContext implements SecurityContext {

    private final Principal principal;

    public SimpleSecurityContext(User user) {
        principal = new UserPrincipal(user);
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
        return false;  // We do not use https, but not important in general.
    }

    @Override
    public String getAuthenticationScheme() {
        return SecurityContext.BASIC_AUTH;
    }
}

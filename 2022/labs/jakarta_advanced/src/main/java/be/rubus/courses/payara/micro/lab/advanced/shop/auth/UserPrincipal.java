package be.rubus.courses.payara.micro.lab.advanced.shop.auth;

import be.rubus.courses.payara.micro.lab.advanced.shop.model.User;

import javax.security.enterprise.CallerPrincipal;

/**
 * A Caller principle that get filled by our User model object.
 */
public class UserPrincipal extends CallerPrincipal {

    public UserPrincipal(User user) {
        super(user.getUserName());
    }
}

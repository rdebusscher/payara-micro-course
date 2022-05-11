package be.rubus.courses.payara.micro.lab.shop.service;

import be.rubus.courses.payara.micro.lab.shop.model.User;
import be.rubus.courses.payara.micro.lab.shop.storage.Database;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 */
@ApplicationScoped
public class UserService {

    @Inject
    private Database database;

    public User getUser() {
        // In this simplified version of the lab, we only have 1 user that is fixed by default.
        return database.getUsers().get(0);
    }

}

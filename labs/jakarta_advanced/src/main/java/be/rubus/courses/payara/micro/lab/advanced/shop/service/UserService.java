package be.rubus.courses.payara.micro.lab.advanced.shop.service;

import be.rubus.courses.payara.micro.lab.advanced.shop.ErrorMessage;
import be.rubus.courses.payara.micro.lab.advanced.shop.model.Credentials;
import be.rubus.courses.payara.micro.lab.advanced.shop.model.User;
import be.rubus.courses.payara.micro.lab.advanced.shop.storage.Database;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Optional;

/**
 *
 */
@ApplicationScoped
public class UserService {

    @Inject
    private Database database;

    public Optional<User> findUserByUserName(String userName) {
        return database.getUsers().stream().filter(u -> u.getUserName().equals(userName)).findAny();
    }

    public ErrorMessage addUser(Credentials credentials) {
        Optional<User> userByName = findUserByUserName(credentials.getUserName());
        if (userByName.isPresent()) {
            // User we want to add already exist.
            return new ErrorMessage("AD11", "User already exists");
        }
        User user = new User();
        user.setUserName(credentials.getUserName());
        user.setName(credentials.getName());
        database.addUser(user);
        return null;
    }
}

package be.rubus.courses.payara.micro.lab.advanced.shop.storage;

import be.rubus.courses.payara.micro.lab.advanced.shop.model.Product;
import be.rubus.courses.payara.micro.lab.advanced.shop.model.User;
import be.rubus.courses.payara.micro.lab.advanced.shop.model.UserItems;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.*;

/**
 * Our database is just an in memory storage.
 */
@ApplicationScoped
public class Database {

    private List<Product> products;
    private List<User> users;
    private List<UserItems> userItems;

    @Inject
    private DataInitializer initializer;

    @PostConstruct
    public void init() {
        products = initializer.createProductList();
        users = new ArrayList<>();
        userItems = new ArrayList<>();
    }

    public List<Product> getProducts() {
        // Wrapped in a new List so that we cannot modify the database contents.
        return new ArrayList<>(products);
    }

    public List<User> getUsers() {
        // Wrapped in a new List so that we cannot modify the database contents.
        return new ArrayList<>(users);
    }

    public void addUser(User user) {
        // We have just checked that user does not exist already. But in production system it should be done again.
        users.add(user);
    }

    public List<UserItems> getUserItems() {
        // Wrapped in a new List so that we cannot modify the database contents.
        return new ArrayList<>(userItems);
    }

    public void addProducts(User user, Collection<Product> products) {
        UserItems userItem = ensureUserItem(user);
        userItem.getProducts().addAll(products);
    }

    private UserItems ensureUserItem(User user) {
        // Find the UserItems entry for the user or create a new one if not found.
        UserItems userItem = userItems.stream()
                .filter(ui -> ui.getUser().equals(user))
                .findAny()
                .orElse(new UserItems());

        if (userItem.getProducts() == null) {
            // A new entry, add basic data.
            userItem.setProducts(new ArrayList<>());
            userItem.setUser(user);
            userItems.add(userItem);
        }
        return userItem;
    }

    public void removeProducts(User user, Collection<Product> products) {
        UserItems userItem = ensureUserItem(user);
        userItem.getProducts().removeAll(products);
    }

}

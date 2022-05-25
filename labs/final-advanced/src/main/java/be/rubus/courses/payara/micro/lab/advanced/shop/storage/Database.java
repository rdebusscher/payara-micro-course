package be.rubus.courses.payara.micro.lab.advanced.shop.storage;

import be.rubus.courses.payara.micro.lab.advanced.shop.model.Product;
import be.rubus.courses.payara.micro.lab.advanced.shop.model.User;
import be.rubus.courses.payara.micro.lab.advanced.shop.model.UserItems;
import one.microstream.integrations.cdi.types.Store;
import one.microstream.integrations.cdi.types.StoreType;
import one.microstream.storage.types.StorageManager;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

/**
 * Our database is operating on the Object Graph that is maintained by MicroStream.
 */
@ApplicationScoped
public class Database {

    private static final Logger LOGGER = Logger.getLogger(Database.class.getName());

    @Inject
    private StorageRoot root;

    @Inject
    private DataInitializer initializer;

    // The created MicroStream StorageManager is now also injectable.
    @Inject
    private StorageManager storageManager;

    @PostConstruct
    public void init() {
        LOGGER.info("**** Executing Init on Database");
        if (root.isEmpty()) {
            LOGGER.info("**** Initialize database and store products");
            root.getProducts().addAll(initializer.createProductList());
            // We can't have an interceptor on PostConstruct. So we do it ourself
            storageManager.store(root.getProducts());
        }

    }

    public List<Product> getProducts() {
        // Wrapped in a new List so that we cannot modify the database contents.
        return new ArrayList<>(root.getProducts());
    }

    public List<User> getUsers() {
        // Wrapped in a new List so that we cannot modify the database contents.
        return new ArrayList<>(root.getUsers());
    }

    @Store(fields = "users")
    public void addUser(User user) {
        // We have just checked that user does not exist already. But in production system it should be done again.
        root.getUsers().add(user);
    }

    public List<UserItems> getUserItems() {
        // Wrapped in a new List so that we cannot modify the database contents.
        return new ArrayList<>(root.getUserItems());
    }

    @Store(fields = "userItems", value = StoreType.EAGER)
    // We need eager as we do also make changes to items of the list which are not picked up
    // by the Lazy evaluator
    public void addProducts(User user, Collection<Product> products) {
        UserItems userItem = ensureUserItem(user);
        userItem.getProducts().addAll(products);
    }

    private UserItems ensureUserItem(User user) {
        // Find the UserItems entry for the user or create a new one if not found.
        UserItems userItem = root.getUserItems().stream()
                .filter(ui -> ui.getUser().equals(user))
                .findAny()
                .orElse(new UserItems());

        if (userItem.getProducts() == null) {
            // A new entry, add basic data.
            userItem.setProducts(new ArrayList<>());
            userItem.setUser(user);
            root.getUserItems().add(userItem);
        }
        return userItem;
    }

    @Store(fields = "userItems", value = StoreType.EAGER)
    // We need eager as we do also make changes to items of the list which are not picked up
    // by the Lazy evaluator
    public void removeProducts(User user, Collection<Product> products) {
        UserItems userItem = ensureUserItem(user);
        userItem.getProducts().removeAll(products);
    }

}

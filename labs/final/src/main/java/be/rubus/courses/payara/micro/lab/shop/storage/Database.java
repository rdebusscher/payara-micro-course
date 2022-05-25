package be.rubus.courses.payara.micro.lab.shop.storage;

import be.rubus.courses.payara.micro.lab.shop.model.Product;
import be.rubus.courses.payara.micro.lab.shop.model.User;
import be.rubus.courses.payara.micro.lab.shop.model.UserItems;
import one.microstream.storage.embedded.types.EmbeddedStorage;
import one.microstream.storage.types.StorageManager;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Our database is just an in memory storage.
 */
@ApplicationScoped
public class Database {

    private static final Logger LOGGER = LoggerFactory.getLogger(Database.class);

    @Inject
    @ConfigProperty(name = "database.location")
    private String databaseLocation;

    private List<User> users;

    private StorageRoot root;

    private StorageManager storageManager;

    @Inject
    private DataInitializer initializer;

    @PostConstruct
    public void init() {
        root = new StorageRoot();
        storageManager = EmbeddedStorage.start(root, Paths.get(databaseLocation));
        if (root.isEmpty()) {
            LOGGER.info("**** Initialize database and store products");
            root.getProducts().addAll(initializer.createProductList());
            storageManager.storeRoot();  // First time storage, we can use storeRoot.
            storageManager.store(root.getProducts());
        }
        users = new ArrayList<>();
        users.add(initializer.createUser());
    }

    public List<Product> getProducts() {
        // Wrapped in a new List so that we cannot modify the database contents.
        return new ArrayList<>(root.getProducts());
    }

    public List<User> getUsers() {
        // Wrapped in a new List so that we cannot modify the database contents.
        return new ArrayList<>(users);
    }

    public List<UserItems> getUserItems() {
        // Wrapped in a new List so that we cannot modify the database contents.
        return new ArrayList<>(root.getUserItems());
    }

    public void addProducts(User user, Collection<Product> products) {
        UserItems userItem = ensureUserItem(user);
        userItem.getProducts().addAll(products);
        LOGGER.info("**** Updating the userItem");
        storageManager.store(userItem.getProducts());  // When we add products to existing UserItem
        // the parent of the change is the UserItem.ProductList
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
            // Make sure we store the changed list to disk
            LOGGER.info("**** Storing the newly added userItem");
            storageManager.store(root.getUserItems());

        }
        return userItem;
    }

    public void removeProducts(User user, Collection<Product> products) {
        UserItems userItem = ensureUserItem(user);
        userItem.getProducts().removeAll(products);

        LOGGER.info("**** Updating the userItem");
        storageManager.store(userItem.getProducts());  // When we delete products from existing UserItem
        // the parent of the change is the UserItem.ProductList itself.

    }

}

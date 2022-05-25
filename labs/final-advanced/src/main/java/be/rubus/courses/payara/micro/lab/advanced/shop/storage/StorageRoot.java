package be.rubus.courses.payara.micro.lab.advanced.shop.storage;


import be.rubus.courses.payara.micro.lab.advanced.shop.model.Product;
import be.rubus.courses.payara.micro.lab.advanced.shop.model.User;
import be.rubus.courses.payara.micro.lab.advanced.shop.model.UserItems;
import one.microstream.integrations.cdi.types.Storage;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the Object root of our database and is maintained by MicroStream.
 */
@Storage
public class StorageRoot {

    private final List<Product> products = new ArrayList<>();

    private final List<User> users = new ArrayList<>();

    private final List<UserItems> userItems = new ArrayList<>();


    public List<Product> getProducts() {
        return products;
    }

    public List<UserItems> getUserItems() {
        return userItems;
    }

    public List<User> getUsers() {
        return users;
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }
}

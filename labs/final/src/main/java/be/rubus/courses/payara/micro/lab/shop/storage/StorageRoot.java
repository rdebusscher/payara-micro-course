package be.rubus.courses.payara.micro.lab.shop.storage;

import be.rubus.courses.payara.micro.lab.shop.model.Product;
import be.rubus.courses.payara.micro.lab.shop.model.UserItems;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the Object root of our database and is maintained by MicroStream.
 */
public class StorageRoot {

    private final List<Product> products = new ArrayList<>();
    private final List<UserItems> userItems = new ArrayList<>();


    public List<Product> getProducts() {
        return products;
    }

    public List<UserItems> getUserItems() {
        return userItems;
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }
}

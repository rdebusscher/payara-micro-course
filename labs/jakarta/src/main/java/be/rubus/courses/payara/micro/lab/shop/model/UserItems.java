package be.rubus.courses.payara.micro.lab.shop.model;

import java.util.List;

/**
 * Keep track of all the products assigned to a User.
 */
public class UserItems {

    private User user;
    private List<Product> products;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

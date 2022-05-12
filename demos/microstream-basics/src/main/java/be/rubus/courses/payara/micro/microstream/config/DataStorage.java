package be.rubus.courses.payara.micro.microstream.config;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class DataStorage {

    private final List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }

    public void addProducts(List<Product> products) {
        this.products.addAll(products);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DataStorage.class.getSimpleName() + "[", "]")
                .add("products=" + products)
                .toString();
    }
}

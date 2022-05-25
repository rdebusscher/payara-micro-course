package be.rubus.courses.payara.micro.lab.advanced.shop.storage;

import be.rubus.courses.payara.micro.lab.advanced.shop.model.Product;
import be.rubus.courses.payara.micro.lab.advanced.shop.model.ProductCategory;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Initializes the database with some products when the application starts.
 */
@ApplicationScoped
public class DataInitializer {

    public List<Product> createProductList() {
        List<Product> result = new ArrayList<>();
        result.add(newProduct("B01", ProductCategory.BIKE, "City Bike", 450.0));
        result.add(newProduct("B02", ProductCategory.BIKE, "Mountain Bike", 1100.0));
        result.add(newProduct("B03", ProductCategory.BIKE, "Race Bike", 2300.0));
        result.add(newProduct("C01", ProductCategory.CAR, "Opel", 21000.0));
        result.add(newProduct("C02", ProductCategory.CAR, "Tesla", 40000.0));
        result.add(newProduct("T01", ProductCategory.TRUCK, "Scania", 110000.0));
        result.add(newProduct("T02", ProductCategory.TRUCK, "Volvo", 75000.0));

        return result;
    }

    private Product newProduct(String id, ProductCategory category, String name, double price) {
        Product result = new Product();
        result.setId(id);
        result.setProductCategory(category);
        result.setName(name);
        result.setPrice(price);
        return result;
    }

}

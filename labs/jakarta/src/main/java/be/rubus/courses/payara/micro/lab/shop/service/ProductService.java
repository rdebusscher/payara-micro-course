package be.rubus.courses.payara.micro.lab.shop.service;

import be.rubus.courses.payara.micro.lab.shop.model.Product;
import be.rubus.courses.payara.micro.lab.shop.model.ProductCategory;
import be.rubus.courses.payara.micro.lab.shop.storage.Database;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class ProductService {

    @Inject
    private Database database;

    /**
     * Find all products that match the filter criteria.  When {@link ProductCategory} specified
     * only the products belonging to that category.
     * when minPrice and maxPrice specified, only products within these limits.
     * When only minPrice specified, all products with a price larger or equal the minPrice are returned.
     * When only maxPrice specified, all products with a price smaller or equal the maxPrice are returned.
     *
     * @param category productCategory to filter, if any.
     * @param minPrice minimum price to filter, if any.
     * @param maxPrice maximum price to filter, if any.
     * @return List of matching products.
     */
    public List<Product> findProducts(ProductCategory category, Double minPrice, Double maxPrice) {
        Predicate<Product> productPriceFilter = product -> {
            if (minPrice != null && maxPrice != null) {
                return product.getPrice() >= minPrice && product.getPrice() <= maxPrice;
            }
            if (minPrice != null) {
                return product.getPrice() >= minPrice;
            } else {
                return product.getPrice() <= maxPrice;
            }
        };

        Stream<Product> productStream = database.getProducts().stream()
                .filter(p -> category == null || p.getProductCategory() == category);
        if (minPrice != null || maxPrice != null) {
            productStream = productStream.filter(productPriceFilter);
        }
        return productStream.collect(Collectors.toList());
    }


    public Product findProductById(String id) {
        return database.getProducts().stream()
                .filter(p -> p.getId().equals(id))
                .findAny()
                .orElse(null);
    }
}

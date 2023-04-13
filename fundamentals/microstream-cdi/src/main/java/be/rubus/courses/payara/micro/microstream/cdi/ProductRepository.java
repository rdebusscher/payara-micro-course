package be.rubus.courses.payara.micro.microstream.cdi;

import be.rubus.courses.payara.micro.microstream.cdi.model.Inventory;
import be.rubus.courses.payara.micro.microstream.cdi.model.Product;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collection;
import java.util.Optional;
import java.util.logging.Logger;

@ApplicationScoped
public class ProductRepository {
    private static final Logger LOGGER = Logger.getLogger(ProductRepository.class.getName());

    @Inject
    private Inventory inventory;

    public Collection<Product> getAll() {
        return this.inventory.getProducts();
    }

    public Product save(Product item) {
        inventory.add(item);
        return item;
    }

    public Optional<Product> findById(long id) {
        LOGGER.info("Finding the item by id: " + id);
        return inventory.findById(id);
    }

    public void deleteById(long id) {
        inventory.deleteById(id);
    }
}

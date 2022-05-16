package be.rubus.microstream.testing;

import be.rubus.microstream.testing.model.Inventory;
import be.rubus.microstream.testing.model.Product;
import one.microstream.integrations.cdi.types.Store;

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

    @Store
    public Product save(Product item) {
        inventory.add(item);
        return item;
    }

    public Optional<Product> findById(long id) {
        LOGGER.info("Finding the item by id: " + id);
        return inventory.findById(id);
    }

    @Store
    public void deleteById(long id) {
        inventory.deleteById(id);
    }
}

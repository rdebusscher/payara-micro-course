package be.rubus.microstream.testing.model;

import one.microstream.integrations.cdi.types.Storage;
import one.microstream.persistence.types.Persister;

import javax.inject.Inject;
import java.util.*;

@Storage
public class Inventory {

    @Inject
    private Persister persister;

    private final Set<Product> products = new HashSet<>();

    public void add(Product product) {
        Objects.requireNonNull(product, "product is required");
        products.add(product);
        persister.store(products);
    }

    public Set<Product> getProducts() {
        return Collections.unmodifiableSet(products);
    }

    public Optional<Product> findById(long id) {
        return products.stream().filter(p -> p.getId() == id).findAny();
    }

    public void deleteById(final long id) {
        products.removeIf(p -> p.getId() == id);
        persister.store(products);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Inventory inventory = (Inventory) o;
        return Objects.equals(this.products, inventory.products);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.products);
    }

    @Override
    public String toString() {
        return "Inventory{"
                +
                "products="
                + this.products
                +
                '}';
    }

}

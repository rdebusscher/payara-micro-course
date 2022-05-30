package be.rubus.courses.payara.micro.microstream.cdi.model;

import one.microstream.integrations.cdi.types.Storage;

import java.util.*;

@Storage
public class Inventory {
    private final Set<Product> products = new HashSet<>();

    public void add(Product product) {
        Objects.requireNonNull(product, "product is required");
        this.products.add(product);
    }

    public Set<Product> getProducts() {
        return Collections.unmodifiableSet(this.products);
    }

    public Optional<Product> findById(long id) {
        return this.products.stream().filter(p -> p.getId() == id).findAny();
    }

    public void deleteById(final long id) {
        this.products.removeIf(p -> p.getId() == id);

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

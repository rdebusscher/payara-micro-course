package be.rubus.courses.payara.micro.microstream.config;

import one.microstream.persistence.types.Storer;
import one.microstream.storage.embedded.configuration.types.EmbeddedStorageConfiguration;
import one.microstream.storage.embedded.types.EmbeddedStorageManager;
import one.microstream.storage.types.*;

import java.util.ArrayList;
import java.util.List;

public class BuilderConfiguration {

    public static void main(String[] args) {
        // Application-specific root instance
        DataStorage root = new DataStorage();

        // Initialize a storage manager ("the database")
        try (StorageManager storageManager = createStorageManager(root)) {

            // Start
            storageManager.start();

            System.out.printf("Number of products %s %n", root.getProducts().size());
            if (root.getProducts().isEmpty()) {
                loadProducts(storageManager);
            } else {
                System.out.println(root.getProducts());
            }

        }

        Product product = getProductById(root, 2);
        System.out.println(product);

        System.out.printf("Number of products at shutdown %s %n", root.getProducts().size());

        // Shutdown is optional as the storage concept is inherently crash-safe
//		storageManager.shutdown();

    }

    private static Product getProductById(DataStorage root, int id) {
        return root.getProducts().stream()
                .filter(p -> p.getId() == id)
                .findAny().orElse(null);
    }

    private static void loadProducts(StorageManager storageManager) {

        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "Bread", ProductType.FOOD));
        products.add(new Product(2, "Orange Juice", ProductType.FOOD));
        products.add(new Product(3, "Floor cleaning", ProductType.CLEANING));
        products.add(new Product(4, "Cinema Tickets", ProductType.ENTERTAINMENT));
        // You can always get the root from the StorageManager.
        DataStorage root = (DataStorage) storageManager.root();
        root.addProducts(products);

        storageManager.store(root.getProducts());

    }

    private static StorageManager createStorageManager(DataStorage root) {

        // requires  microstream-storage-embedded-configuration dependency
        return EmbeddedStorageConfiguration.Builder()
                .setStorageDirectory("target/data2")
                .setChannelCount(4)

                .createEmbeddedStorageFoundation()
                // Further customise the Foundation if needed.
                //.onConnectionFoundation()
                .setRoot(root)

                .createEmbeddedStorageManager();
    }
}

package be.rubus.courses.payara.micro.microstream.config;

import one.microstream.afs.nio.types.NioFileSystem;
import one.microstream.storage.embedded.types.EmbeddedStorageFoundation;
import one.microstream.storage.embedded.types.EmbeddedStorageManager;
import one.microstream.storage.types.*;

import java.util.ArrayList;
import java.util.List;

public class FullConfiguration {

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

        System.out.printf("Number of products at shutdown %s %n", root.getProducts().size());

        // Shutdown is optional as the storage concept is inherently crash-safe
//		storageManager.shutdown();

    }

    private static void loadProducts(StorageManager storageManager) {

        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "Bread", ProductType.FOOD));
        products.add(new Product(2, "Orange Juice", ProductType.FOOD));
        products.add(new Product(3, "Floor cleaning", ProductType.CLEANING));
        products.add(new Product(3, "Cinema Tickets", ProductType.ENTERTAINMENT));
        // You can always get the root from the StorageManager.
        DataStorage root = (DataStorage) storageManager.root();
        root.addProducts(products);

        storageManager.store(root.getProducts());
    }

    private static EmbeddedStorageManager createStorageManager(DataStorage root) {
        NioFileSystem fileSystem = NioFileSystem.New();
        return EmbeddedStorageFoundation.New()
                .setConfiguration(
                        StorageConfiguration.Builder()
                                .setStorageFileProvider(
                                        Storage.FileProviderBuilder(fileSystem)
                                                .setDirectory(fileSystem.ensureDirectoryPath("target/data2"))
                                                .createFileProvider()
                                )
                                .setChannelCountProvider(StorageChannelCountProvider.New(2))
                                .setBackupSetup(StorageBackupSetup.New(
                                        fileSystem.ensureDirectoryPath("target/backupDir")
                                ))
                                .createConfiguration()
                )
                .setRoot(root)
                .createEmbeddedStorageManager();
    }
}

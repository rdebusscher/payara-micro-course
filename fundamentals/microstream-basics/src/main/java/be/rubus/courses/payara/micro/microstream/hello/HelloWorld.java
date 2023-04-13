package be.rubus.courses.payara.micro.microstream.hello;

import one.microstream.storage.embedded.types.EmbeddedStorage;
import one.microstream.storage.types.StorageManager;

import java.nio.file.Paths;
import java.util.Date;

public class HelloWorld {

    public static void main(String[] args) {
        // Application-specific root instance
        DataRoot root = new DataRoot("");

        // Initialize a storage manager ("the database") with the given directory and defaults for everything else.
        try (StorageManager storageManager = EmbeddedStorage.start(root, Paths.get("target/data"))) {

            // print the root to show its loaded content (stored in the last execution).
            System.out.printf("database content at load : %s %n", root);

            // Set content data to the root element, including the time to visualize changes on the next execution.
            root.setValue2("Hello World! @ " + new Date());


            // Store the modified root and its content.
            storageManager.store(root);

            System.out.printf("Memory content at shutdown : %s %n", root);
        }

        // Shutdown is optional as the storage concept is inherently crash-safe
//		storageManager.shutdown();

    }
}

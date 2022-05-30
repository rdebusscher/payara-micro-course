package be.rubus.courses.payara.micro.microstream.lazy;

import one.microstream.memory.MemoryStatistics;
import one.microstream.memory.MemoryStatisticsProvider;
import one.microstream.memory.XMemory;
import one.microstream.storage.embedded.types.EmbeddedStorage;
import one.microstream.storage.types.StorageManager;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LazyTest {

    public static void main(String[] args) {
        // Application-specific root instance
        LazyRoot root = new LazyRoot();

                System.out.printf("Available Heap (Before MicroStream started) %,d %n", Runtime.getRuntime().freeMemory());
        // Initialize a storage manager ("the database") with the given directory and defaults for everything else.
        try (StorageManager storageManager = EmbeddedStorage.start(root, Paths.get("target/lazyData"))) {

            if (root.getAlwaysAvailable() == null) {
                loadTestData(storageManager);
            } else {
                System.out.printf("Available Heap (With MicroStream started) %,d %n", Runtime.getRuntime().freeMemory());
                System.out.printf("Is Lazy data loaded ? %s %n", root.getLazy().isLoaded());
                System.out.printf("Available Heap (isLoaded does not trigger the load) %,d %n", Runtime.getRuntime().freeMemory());
                System.out.printf("Size %s %n", root.getLazy().get().size());
                System.out.printf("Available Heap (with Lazy data loaded) %,d %n", Runtime.getRuntime().freeMemory());
            }

        }

    }

    private static void loadTestData(StorageManager storageManager) {
        System.out.printf("Available Heap at start of creating test data %,d %n", Runtime.getRuntime().freeMemory());

        LazyRoot root = (LazyRoot) storageManager.root();
        root.setAlwaysAvailable("Lazy data are created");

        root.getLazy().get().addAll(createData());

        storageManager.storeRoot();  // For the String update
        storageManager.store(root.getLazy().get());  // For the updated data in the Lazy List

        System.out.printf("Size %s %n", root.getLazy().get().size());
        System.gc();
        System.out.printf("Available Heap at end of creating test data %,d %n", Runtime.getRuntime().freeMemory());

        System.gc();
        root.getLazy().clear();

        System.gc();  // .gc is a hint, does not mean it does clean up garbage.
        System.out.printf("Available Heap when Lazy is cleared (object no longer in memory) %,d %n", Runtime.getRuntime().freeMemory());
        System.out.println(XMemory.memoryAccessor().createHeapMemoryStatistics().used());
    }

    private static List<String> createData() {
        int count = 50000;
        // This should fit into memory

        List<String> data = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            data.add(getAlphaNumericString(2048));
        }

        return data;
    }

    static String getAlphaNumericString(int n) {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
}

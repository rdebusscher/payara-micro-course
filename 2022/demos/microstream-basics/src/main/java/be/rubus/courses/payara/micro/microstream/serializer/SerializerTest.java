package be.rubus.courses.payara.micro.microstream.serializer;

import one.microstream.persistence.binary.util.Serializer;
import one.microstream.persistence.binary.util.SerializerFoundation;

public class SerializerTest {

    public static void main(String[] args) {
        Employee theBoss = createObjectGraph();

        SerializerFoundation<?> foundation = SerializerFoundation.New()
                .registerEntityTypes(Employee.class);
        Employee reconstructed;
        try (Serializer<byte[]> serializer = Serializer.Bytes(foundation)) {

            byte[] data = serializer.serialize(theBoss);

            reconstructed = serializer.deserialize(data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        printHierarchy(reconstructed);

        System.out.printf("Are this the same objects ? %s %n", theBoss == reconstructed);
        System.out.printf("Are this the objects equals ? %s %n", theBoss.equals(reconstructed));

    }

    private static Employee createObjectGraph() {
        Employee theBoss = new Employee(1L, "The boss");

        Employee employee1 = new Employee(2L, "Person X");
        Employee employee2 = new Employee(3L, "Person Y");
        Employee employee3 = new Employee(4L, "Person Z");

        employee3.setManager(employee2);

        employee1.setManager(theBoss);
        employee2.setManager(theBoss);

        printHierarchy(theBoss);
        return theBoss;
    }

    private static void printHierarchy(Employee manager) {
        System.out.println(manager);
        manager.getEmployees().forEach(SerializerTest::printHierarchy);
    }
}

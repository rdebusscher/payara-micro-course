package be.rubus.courses.payara.micro.microstream.serializer;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Employee {

    private long id;
    private String name;
    private List<Employee> employees = new ArrayList<>();
    private Employee manager;

    public Employee() {
        throw new RuntimeException("Should not be called as this would mean we could have Security Vulnerability");
    }

    public Employee(long id, String name) {
        this.id = id;
        this.name = name;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
        manager.getEmployees().add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Employee)) {
            return false;
        }

        Employee person = (Employee) o;

        return name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Employee.class.getSimpleName() + "[", "]")
                .add("pointer=" + System.identityHashCode(this))
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("manager = '" + (manager == null ? " - " : manager.getName()) + "'")
                .add("directReports = '" + employees.stream().map(Employee::getName).collect(Collectors.joining(" - ")) + "'")
                .toString();
    }
}

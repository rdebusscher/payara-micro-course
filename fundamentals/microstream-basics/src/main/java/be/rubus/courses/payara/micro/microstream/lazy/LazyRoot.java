package be.rubus.courses.payara.micro.microstream.lazy;

import one.microstream.reference.Lazy;

import java.util.ArrayList;
import java.util.List;

public class LazyRoot {

    private String alwaysAvailable;
    private Lazy<List<String>> lazy = Lazy.Reference(new ArrayList<>());


    // Order for customer
    // Map<String, Lazy<List<Order>>> ordersByCustomer.
    // ordersByCustomer.get(customerId).get(); -> List<Order>

    // Map<MonthYear, Lazy<List<Order>>> ordersByMonth
    // ordersByMonth.get(May2022).get();

    // No Mapping
    // No 'external' system (primary)
    // Query performance speed
           //Query 3 tables, where -> JPA / MicroStream  1400/1

    public String getAlwaysAvailable() {
        return alwaysAvailable;
    }

    public void setAlwaysAvailable(String alwaysAvailable) {
        this.alwaysAvailable = alwaysAvailable;
    }

    public Lazy<List<String>> getLazy() {
        return lazy;
    }

    public void setLazy(Lazy<List<String>> lazy) {
        this.lazy = lazy;
    }
}

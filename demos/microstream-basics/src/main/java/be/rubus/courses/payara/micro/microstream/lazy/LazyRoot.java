package be.rubus.courses.payara.micro.microstream.lazy;

import one.microstream.reference.Lazy;

import java.util.ArrayList;
import java.util.List;

public class LazyRoot {

    private String alwaysAvailable;
    private Lazy<List<String>> lazy = Lazy.Reference(new ArrayList<>());

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

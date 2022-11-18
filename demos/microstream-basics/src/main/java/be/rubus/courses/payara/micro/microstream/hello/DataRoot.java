package be.rubus.courses.payara.micro.microstream.hello;

public class DataRoot {

    public DataRoot() {
        throw new RuntimeException("Can't do");
    }

    public DataRoot(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return this.value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "DataRoot{" +
                "content='" + value + '\'' +
                '}';
    }
}

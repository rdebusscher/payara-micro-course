package be.rubus.courses.payara.micro.microstream.hello;

public class DataRoot {

    public DataRoot() {
        throw new RuntimeException("Can't do");
    }

    public DataRoot(String value2) {
        this.value2 = value2;
    }

    private String value2;

    public String getValue2() {
        return this.value2;
    }

    public void setValue2(final String value2) {
        this.value2 = value2;
    }

    @Override
    public String toString() {
        return "DataRoot{" +
                "content='" + value2 + '\'' +
                '}';
    }
}

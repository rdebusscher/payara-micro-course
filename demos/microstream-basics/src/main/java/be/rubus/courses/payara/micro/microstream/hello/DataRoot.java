package be.rubus.courses.payara.micro.microstream.hello;

public class DataRoot {

    private String content;

    public String getContent() {
        return this.content;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "DataRoot{" +
                "content='" + content + '\'' +
                '}';
    }
}

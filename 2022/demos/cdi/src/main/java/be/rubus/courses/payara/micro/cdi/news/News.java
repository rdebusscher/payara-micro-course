package be.rubus.courses.payara.micro.cdi.news;

import java.util.Objects;


public final class News {

    private final String topic;

    private News(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    public static News of(String topic) {
        Objects.requireNonNull(topic, "topic is required");
        return new News(topic);
    }
}

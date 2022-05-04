package be.rubus.courses.payara.micro.cdi;

import be.rubus.courses.payara.micro.cdi.news.Journalist;
import be.rubus.courses.payara.micro.cdi.news.News;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

public class App4 {

    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            Journalist journalist = container.select(Journalist.class).get();
            journalist.announceNews(News.of("MicroStream 7 has arrived!!"));
            journalist.specificNews(News.of("That is a exclusive interview of the spec leader of Jakarta EE 10"));
        }
    }
}

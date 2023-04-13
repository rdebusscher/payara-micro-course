package be.rubus.courses.payara.micro.jaxrs.cdi;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MyService {


    public String getGreeting(String name, String language) {
        String pattern;
        switch (language) {
            case "nl":
                pattern = "Goedemorgen %s";
                break;
            case "fr":
                pattern = "Bonjour %s";
                break;
            case "de":
                pattern = "Guten Morgen %s";
                break;
            default:
                pattern = "Good morning %s";
        }
        return String.format(pattern, name);
    }
}

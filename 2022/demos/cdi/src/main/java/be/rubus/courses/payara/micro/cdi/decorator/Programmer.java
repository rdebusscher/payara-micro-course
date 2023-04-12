package be.rubus.courses.payara.micro.cdi.decorator;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Programmer implements Worker {

    @Override
    public String work(String job) {
        return "A programmer has received a job, it will convert coffee in code: " + job;
    }
}

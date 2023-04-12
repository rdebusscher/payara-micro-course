package be.rubus.courses.payara.micro.cdi.decorator;

import javax.annotation.Priority;
import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import javax.interceptor.Interceptor;

@Decorator
@Priority(Interceptor.Priority.APPLICATION)
public class Manager implements Worker {

    @Inject
    @Delegate  // required to get the actual CDI bean.
    @Any // Specification requirement to be able to handle all instances (including qualified ones)
    private Worker worker;

    @Override
    public String work(String job) {
        return "A manager has received a job and it will delegate -> " + worker.work(job);
    }
}

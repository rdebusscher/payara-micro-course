package be.rubus.courses.payara.micro.cdi.audited;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.logging.Logger;

@Timed  // Need the Interceptor Binding
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)  // Ordering of interceptors.
public class TimedInterceptor {

    private static final Logger LOGGER = Logger.getLogger(TimedInterceptor.class.getName());

    @AroundInvoke
    public Object auditMethod(InvocationContext ctx) throws Exception {
        long start = System.nanoTime();
        Object result = ctx.proceed();  // The actual method.
        long end = System.nanoTime();

        String message = String.format("Time to execute the class %s, the method %s is of %s",
                ctx.getTarget().getClass(), ctx.getMethod(), prettyPrint(end - start));
        LOGGER.info(message);
        return result;
    }

    private String prettyPrint(long duration) {
        return (duration / 1_000_000) + " ms " +
                (duration % 1_000_000) + " ns ";
    }
}

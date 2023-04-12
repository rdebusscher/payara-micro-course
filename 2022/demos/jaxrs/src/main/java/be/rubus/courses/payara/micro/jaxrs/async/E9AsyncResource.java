package be.rubus.courses.payara.micro.jaxrs.async;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Path("async")
public class E9AsyncResource {

    @Resource
    private ManagedExecutorService executor;

    @GET
    @Path("longRunning")
    public CompletionStage<String> longRunning() {
        // Does not block the thread of the runtime.
        CompletableFuture<String> future = new CompletableFuture<>();
        executor.submit(() -> {
            Thread.sleep(100);
            future.complete("Welcome to the async world");
            return null;
        });
        // project loom :  JDK19
        // Virtual Threads , Structured concurrency
        return future;
    }

}

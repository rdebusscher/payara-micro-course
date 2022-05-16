/*
 * Copyright 2019 Rudy De Busscher (Payara Services)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package be.rubus.courses.payara.micro.rest.client.async;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

/**
 *
 */
@Path("/client-async")
@ApplicationScoped
public class ClientAsyncController {

    @Inject
    @RestClient
    private HeavyService heavyService;

    @GET
    public String useAsync() {
        CompletionStage<String> stage = heavyService.calculate();
        System.out.println("Continue in caller");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();  // FIXME Demo code
        }

        System.out.println("Ready for retrieving result");
        String result;
        try {
            result = stage.toCompletableFuture().get();
        } catch (InterruptedException | ExecutionException e) {
            result = e.getMessage();
        }
        System.out.println("received result");
        return result;
    }
}

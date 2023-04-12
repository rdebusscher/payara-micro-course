/*
 * Copyright (c) 2021 Rudy De Busscher
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
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
package org.example.app.it;

import be.rubus.microstream.testing.model.Product;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Interface that describes the endpoints of our application. Is used in combination with MicroProfile Rest client
 * to make calls to endpoints of our application easier.
 */
@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ProductService {

    @GET
    List<Product> getAllProducts();

    @GET
    @Path("{id}")
    Product findById(@PathParam("id") long id);

    @POST
    Response insert(Product product);

    @DELETE
    @Path("{id}")
    Response delete(@PathParam("id") long id);

}

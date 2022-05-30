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
package be.rubus.courses.payara.micro.rest.client;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.logging.Logger;

@Path("/client/heavy")
public class HeavyCalculationController {

    private static final Logger LOGGER = Logger.getLogger(HeavyCalculationController.class.getName());

    @GET
    public String calculate() {
        LOGGER.info("Received request for Heavy Service");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();  // FIXME Demo code
        }
        LOGGER.info("Returning result from Heavy service");
        return "The result after some substantial amount of calculation";
    }


}

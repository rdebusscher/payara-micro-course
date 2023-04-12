package be.rubus.courses.payara.micro.jaxrs.validation;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;

@Path("/validate")
public class E4ValidationResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public void testValue(@NotNull @QueryParam("day") Integer day, @NotNull @QueryParam("dayOfWeek") WeekDay weekday) {
        LocalDate dayOfMonth = LocalDate.now().withDayOfMonth(day);
        if (dayOfMonth.getDayOfWeek() != weekday.getDayOfWeek()) {
            throw new WebApplicationException("Invalid day", Response.Status.PRECONDITION_FAILED);
        }
    }
}

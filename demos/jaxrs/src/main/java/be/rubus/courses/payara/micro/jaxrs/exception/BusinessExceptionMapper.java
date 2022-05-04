package be.rubus.courses.payara.micro.jaxrs.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BusinessExceptionMapper implements ExceptionMapper<BusinessException> {
    @Override
    public Response toResponse(BusinessException exception) {
        // Ideally, we need to have a proper JSON result indicating message and code.
        return Response.status(Response.Status.PRECONDITION_FAILED).entity(exception.getMessage()).build();
    }
}

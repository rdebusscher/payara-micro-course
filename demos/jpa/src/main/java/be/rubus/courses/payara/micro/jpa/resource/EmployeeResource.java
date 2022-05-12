package be.rubus.courses.payara.micro.jpa.resource;

import be.rubus.courses.payara.micro.jpa.model.Employee;
import be.rubus.courses.payara.micro.jpa.service.EmployeeService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

@Path("/employee")
@RequestScoped
    @Produces(MediaType.APPLICATION_JSON)
public class EmployeeResource {

    @Inject
    private EmployeeService employeeService;

    @GET
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @Path("/{employeeId}")
    @GET
    public Response getEmployee(@PathParam("employeeId")Long employeeId) {
        Employee employee = employeeService.getEmployee(employeeId);
        if (employee == null) {
            // Employee with Id not found so the URL doesn't exist.
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(employee).build();
        }
    }

    @Path("/{employeeId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public void updateEmployee(@PathParam("employeeId")Long employeeId, Employee employee) {
        employeeService.updateEmployee(employee);
    }

    @Path("/company/{companyId}")
    @GET
    public List<Employee> getEmployeesOfCompany(@PathParam("companyId")Long companyId) {
        return employeeService.getEmployeesOfCompany(companyId);
    }

}

package be.rubus.courses.payara.micro.jpa.resource;

import be.rubus.courses.payara.micro.jpa.model.Company;
import be.rubus.courses.payara.micro.jpa.service.CompanyService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.util.List;


@Path("/company")
@RequestScoped
public class CompanyResource {

    @Inject
    private CompanyService companyService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Company getCompany(@PathParam("id") Long id) {
        return companyService.findCompany(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Company> getAllCompany() {
        return companyService.allCompanies();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Company getAllCompany(Company company) {
        return companyService.insertCompany(company);
    }

}

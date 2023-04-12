package be.rubus.courses.payara.micro.jpa.service;

import be.rubus.courses.payara.micro.jpa.model.Company;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.util.List;

/**
 *
 */
@ApplicationScoped  // Commit at end of method or rollback in case of Exception
@Transactional
public class CompanyService {

    @PersistenceContext(name = "TestUnit")
    private EntityManager em;

    public Company findCompany(Long id) {
        return em.find(Company.class, id);
    }

    public List<Company> allCompanies() {
        return em.createQuery("SELECT c FROM Company c", Company.class).getResultList();
    }

    public Company insertCompany(Company company) {
        em.persist(company);
        return company;
    }
}

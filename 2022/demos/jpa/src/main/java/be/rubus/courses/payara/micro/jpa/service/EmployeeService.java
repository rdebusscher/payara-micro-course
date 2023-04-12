package be.rubus.courses.payara.micro.jpa.service;

import be.rubus.courses.payara.micro.jpa.model.Employee;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import java.util.List;

@Transactional  // Commit at end of method or rollback in case of Exception
@ApplicationScoped
public class EmployeeService {

    @PersistenceContext
    private EntityManager em;

    public List<Employee> getAllEmployees() {
        return em.createQuery("SELECT e FROM Employee e JOIN FETCH e.company", Employee.class).getResultList();
    }

    public Employee getEmployee(Long id) {
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e WHERE e.id = :id", Employee.class);
        query.setParameter("id", id);
        //query.getSingleResult();  // Throws NoResultException when not found
        List<Employee> employees = query.getResultList();
        // We could use Optional<> here
        return employees.isEmpty() ? null : employees.get(0);
    }

    public void updateEmployee(Employee employee) {
        if (employee.getCompany() == null) {
            Employee dbEmployee = getEmployee(employee.getId());
            if (dbEmployee != null) {
                employee.setCompany(dbEmployee.getCompany());
            } else {
                // merge will fail also since entity does not exists.
                // FIXME Handle properly through Exceptions
            }
        }
        em.merge(employee);
    }

    public List<Employee> getEmployeesOfCompany(Long companyId) {
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e WHERE e.company.id = :companyId", Employee.class);
        query.setParameter("companyId", companyId);
        return query.getResultList();
    }
}

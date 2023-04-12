package be.rubus.courses.payara.micro.jpa.model;

import javax.persistence.*;

import java.io.Serializable;

/**
 *
 */
@Entity
@Table(name = "company")
public class Company implements Serializable {

    @Id
    @TableGenerator(name = "companyGen", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "CompanyGen", initialValue = 10, allocationSize = 1)
    @GeneratedValue(generator = "companyGen")
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

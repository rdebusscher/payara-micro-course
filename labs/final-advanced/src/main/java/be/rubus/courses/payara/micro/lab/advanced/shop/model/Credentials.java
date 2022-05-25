package be.rubus.courses.payara.micro.lab.advanced.shop.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Models the User credentials when a new user is created through the Rest Endpoints.
 */
public class Credentials {

    @NotNull
    @Size(min = 3)
    private String userName;
    @NotNull
    @Size(min = 3)
    private String name;

    @NotNull
    @Size(min = 8)
    private String password;

    // Getters and setters for JSON-B support.
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

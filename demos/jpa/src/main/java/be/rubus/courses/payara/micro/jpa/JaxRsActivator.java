package be.rubus.courses.payara.micro.jpa;

import javax.annotation.sql.DataSourceDefinition;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


@DataSourceDefinition(name="java:global/jdbc/cloud-mysql",
        className="com.mysql.jdbc.jdbc2.optional.MysqlDataSource",
        url = "${MPCONFIG=ds_url}",
        user="${MPCONFIG=ds_username}",
        password="${MPCONFIG=ds_password}",
        maxPoolSize = 4,
        minPoolSize = 2
)

@ApplicationPath("/rest")
public class JaxRsActivator extends Application {
    /* class body intentionally left blank */
}

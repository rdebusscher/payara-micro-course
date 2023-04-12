package org.example.app.it;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * Specialised Generic Container for Payara Micro.  You do not need to define all the configuration
 * options in the test itself.
 *
 */
public class PayaraMicroContainer extends GenericContainer<PayaraMicroContainer> {

    public PayaraMicroContainer(MountableFile warFile) {
        this(warFile, false);

    }

    public PayaraMicroContainer(MountableFile warFile, boolean debug) {
        super(DockerImageName.parse("payara/micro:5.2022.4-jdk11"));
        withExposedPorts(8080);

        withCopyFileToContainer(warFile, "/opt/payara/deployments/app.war");
        // Health point of Payara Micro based on MicroProfile Health
        waitingFor(Wait.forHttp("/health"));
        withCommand("--deploy /opt/payara/deployments/app.war --noCluster --contextRoot /");
        // Deploy app, no clustering = faster and define context root.

        if (debug) {
            addFixedExposedPort(5005, 5005);
            withEnv("JVM_ARGS", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=*:5005");
            withStartupTimeout(Duration.of(60, ChronoUnit.SECONDS));
        }
    }
}

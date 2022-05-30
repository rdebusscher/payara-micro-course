package org.example.app.it;

import be.rubus.microstream.testing.model.Product;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.assertj.core.api.Assertions;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;

import java.net.URI;
import java.nio.file.Paths;
import java.util.List;


@Testcontainers
public class ProductControllerIT {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductControllerIT.class);

    // A link to the Web application for TestContainers so that it can be added to Payara Micro Image.
    private static final MountableFile WAR_FILE = MountableFile.forHostPath(Paths.get("target/myservice.war").toAbsolutePath(), 0777);

    @Container
    public static GenericContainer container = new GenericContainer(DockerImageName.parse("payara/micro:5.2022.2-jdk11"))
            .withExposedPorts(8080)
            .withCopyFileToContainer(WAR_FILE, "/opt/payara/deployments/app.war")
            .waitingFor(Wait.forHttp("/health"))  // Health point of Payara Micro based on MicroProfile Health
            .withCommand("--deploy /opt/payara/deployments/app.war --noCluster --contextRoot /");  // Deploy app, no clustering = faster and define context root.

    public static ProductService service;

    @BeforeEach
    public void setup() {
        // Since contextroot is / and Application mapping also, our application is accessible on host directly
        URI baseURI = URI.create(String.format("http://localhost:%s", container.getMappedPort(8080)));
        //testContainers maps the standard port to some random port, so request it from Test Containers.
        service = RestClientBuilder.newBuilder().  // From MicroProfile Rest Client
                register(JacksonJsonProvider.class).  // Support JSON-B
                baseUri(baseURI).
                build(ProductService.class);  // Create proxy based on the interface and information of the endpoints.

        Slf4jLogConsumer logConsumer = new Slf4jLogConsumer(LOGGER);
        container.followOutput(logConsumer);  // Show log of container in output.

    }

    @Test
    public void test() {
        List<Product> products = service.getAll();
        Assertions.assertThat(products).isEmpty();

        Product product = new Product();
        product.setId(1L);
        product.setDescription("A fruit");
        product.setName("banana");
        product.setRating(5);
        service.insert(product);

        products = service.getAll();
        Assertions.assertThat(products).hasSize(1);
        Product productFromApp = products.get(0);
        Assertions.assertThat(productFromApp.getId()).isEqualTo(product.getId());
        Assertions.assertThat(productFromApp.getName()).isEqualTo(product.getName());
        Assertions.assertThat(productFromApp.getDescription()).isEqualTo(product.getDescription());
    }

}
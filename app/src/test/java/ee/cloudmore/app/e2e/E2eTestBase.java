package ee.cloudmore.app.e2e;

import ee.cloudmore.app.config.KafkaTestContainerConfiguration;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;

@Tag("e2e-test")
@ActiveProfiles({"e2e-test"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class E2eTestBase extends KafkaTestContainerConfiguration {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUpRestAssureIntegrationTest() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    protected static String string(Resource resource) {
        try (InputStream stream = resource.getInputStream()) {
            return new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}

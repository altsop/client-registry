package ee.cloudmore.app.integration;


import ee.cloudmore.app.config.KafkaTestContainerConfiguration;
import org.junit.jupiter.api.Tag;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@Tag("integration-test")
@ActiveProfiles({"integration-test"})
public abstract class IntegrationTestBase extends KafkaTestContainerConfiguration {
}

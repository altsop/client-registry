package ee.cloudmore.app.integration;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles({"integration-test"})
public abstract class IntegrationTestBase {
}

package ee.cloudmore.app.e2e;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles({"e2e-test"})
public abstract class E2eTestBase {
}

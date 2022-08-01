package ee.cloudmore.app.e2e.registration;

import ee.cloudmore.app.client.ClientRepository;
import ee.cloudmore.app.client.model.Client;
import ee.cloudmore.app.e2e.E2eTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

public class ClientRegistrationFlowE2eTest extends E2eTestBase {

    @Value("classpath:payload/client/registrationRequest.json")
    protected Resource registrationRequest;

    @Autowired
    ClientRepository clientRepository;

    @Test
    @Transactional
    public void shouldSuccessfullySaveEntity() {
        RestAssured
                .with()
                .body(string(registrationRequest)).contentType(ContentType.JSON)
                .when().post("/api/v1/client/register")
                .then()
                .statusCode(200);

        await()
                .atMost(5, TimeUnit.SECONDS)
                .untilAsserted(() -> {
                    Client client = clientRepository.findClientByNameAndSurname("John", "Black");
                    assertThat(client).isNotNull();
                    assertThat(client.getName()).isEqualTo("John");
                    assertThat(client.getSurname()).isEqualTo("Black");
                    assertThat(client.getWage()).isEqualTo(BigDecimal.valueOf(781.44));
                });
    }

}

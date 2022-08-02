package ee.cloudmore.app.integration.registration;

import ee.cloudmore.app.client.model.Client;
import ee.cloudmore.app.client.model.ClientRegistrationDto;
import ee.cloudmore.app.client.registration.ClientRegistrationHandler;
import ee.cloudmore.app.integration.IntegrationTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestPropertySource(properties = {"client.registration.wage.tax=40",})
class ClientRegistrationHandlerIntegrationTest extends IntegrationTestBase {

    @Autowired
    ClientRegistrationHandler clientRegistrationHandler;

    @Test
    void shouldSuccessfullyProcess() {
        ClientRegistrationDto dto = new ClientRegistrationDto()
                .setName("John")
                .setSurname("Black")
                .setWage(BigDecimal.TEN)
                .setEventTime(Instant.parse("2018-11-30T18:35:24.00Z"));

        Client savedClient = clientRegistrationHandler.process(dto);

        assertNotNull(savedClient.getUuid());
        assertThat(savedClient.getName()).isEqualTo("John");
        assertThat(savedClient.getSurname()).isEqualTo("Black");
        assertThat(savedClient.getEventTime()).isEqualTo(Instant.parse("2018-11-30T18:35:24.00Z"));
        assertThat(savedClient.getWage())
                .usingComparator(BigDecimal::compareTo)
                .isEqualTo(BigDecimal.valueOf(14));
    }

}
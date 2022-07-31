package ee.cloudmore.app.client.registration;

import ee.cloudmore.app.IntegrationTestBase;
import ee.cloudmore.app.client.model.Client;
import ee.cloudmore.app.client.model.ClientRegistrationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

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
                .setEventTime(OffsetDateTime.parse("2017-12-03T10:15:30+01:00"));

        Client savedClient = clientRegistrationHandler.process(dto);

        assertNotNull(savedClient.getUuid());
        assertThat(savedClient.getName()).isEqualTo("John");
        assertThat(savedClient.getSurname()).isEqualTo("Black");
        assertThat(savedClient.getEventTime()).isEqualTo(OffsetDateTime.parse("2017-12-03T10:15:30+01:00"));
        assertThat(savedClient.getWage())
                .usingComparator(BigDecimal::compareTo)
                .isEqualTo(BigDecimal.valueOf(14));
    }

}
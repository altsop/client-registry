package ee.cloudmore.app.unit.registration;

import ee.cloudmore.app.client.ClientRepository;
import ee.cloudmore.app.client.model.Client;
import ee.cloudmore.app.client.model.ClientRegistrationDto;
import ee.cloudmore.app.client.registration.ClientRegistrationHandler;
import ee.cloudmore.app.client.registration.ClientRegistrationProperties;
import ee.cloudmore.app.unit.UnitTestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ClientRegistrationHandlerUnitTest extends UnitTestBase {

    @Mock
    ClientRepository clientRepository;

    @Mock
    ClientRegistrationProperties clientRegistrationProperties;

    @Captor
    ArgumentCaptor<Client> clientArgumentCaptor;

    ClientRegistrationHandler registrationHandler;

    @BeforeEach
    public void setup() {
        when(clientRegistrationProperties.getWage())
                .thenReturn(new ClientRegistrationProperties.Wage(BigDecimal.TEN));

        registrationHandler = new ClientRegistrationHandler(
                clientRepository,
                clientRegistrationProperties
        );
    }

    @Test
    void shouldSuccessfullyProcess() {
        ClientRegistrationDto dto = new ClientRegistrationDto()
                .setName("John")
                .setSurname("Black")
                .setWage(BigDecimal.ONE)
                .setEventTime(Instant.parse("2018-11-30T18:35:24.00Z"));

        registrationHandler.process(dto);
        verify(clientRepository).save(clientArgumentCaptor.capture());
        Client clientToBeSaved = clientArgumentCaptor.getValue();

        assertThat(clientToBeSaved.getName()).isEqualTo("John");
        assertThat(clientToBeSaved.getSurname()).isEqualTo("Black");
        assertThat(clientToBeSaved.getEventTime()).isEqualTo(Instant.parse("2018-11-30T18:35:24.00Z"));
        assertThat(clientToBeSaved.getWage())
                .usingComparator(BigDecimal::compareTo)
                .isEqualTo(BigDecimal.valueOf(1.1));
    }

}
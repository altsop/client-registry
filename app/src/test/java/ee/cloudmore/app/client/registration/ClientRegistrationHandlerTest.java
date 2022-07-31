package ee.cloudmore.app.client.registration;

import ee.cloudmore.app.client.ClientRepository;
import ee.cloudmore.app.client.model.Client;
import ee.cloudmore.app.client.model.ClientRegistrationDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientRegistrationHandlerTest {

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
                .setEventTime(OffsetDateTime.parse("2017-12-03T10:15:30+01:00"));

        registrationHandler.process(dto);
        verify(clientRepository).save(clientArgumentCaptor.capture());
        Client clientToBeSaved = clientArgumentCaptor.getValue();

        assertThat(clientToBeSaved.getName()).isEqualTo("John");
        assertThat(clientToBeSaved.getSurname()).isEqualTo("Black");
        assertThat(clientToBeSaved.getEventTime()).isEqualTo(OffsetDateTime.parse("2017-12-03T10:15:30+01:00"));
        assertThat(clientToBeSaved.getWage())
                .usingComparator(BigDecimal::compareTo)
                .isEqualTo(BigDecimal.valueOf(1.1));
    }

}
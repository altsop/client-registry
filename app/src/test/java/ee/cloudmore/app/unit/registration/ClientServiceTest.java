package ee.cloudmore.app.unit.registration;

import ee.cloudmore.app.client.ClientRepository;
import ee.cloudmore.app.client.ClientService;
import ee.cloudmore.app.client.model.Client;
import ee.cloudmore.app.client.model.ClientRegistrationDto;
import ee.cloudmore.app.client.queue.ClientProducer;
import ee.cloudmore.app.exception.BusinessException;
import ee.cloudmore.app.unit.UnitTestBase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ClientServiceTest extends UnitTestBase {

    @Mock
    ClientProducer clientProducer;

    @Mock
    ClientRepository clientRepository;

    @Captor
    ArgumentCaptor<ClientRegistrationDto> clientRegistrationDtoArgumentCaptor;

    ClientService clientService;

    @BeforeEach
    public void setup() {
        clientService = new ClientService(
                clientProducer,
                clientRepository
        );
    }

    @Test
    void shouldDetectDuplicate() {
        ClientRegistrationDto dto = new ClientRegistrationDto()
                .setName("John")
                .setSurname("Black")
                .setWage(BigDecimal.ONE)
                .setEventTime(Instant.parse("2018-11-30T18:35:24.00Z"));

        when(clientRepository.findAllByEventTime(Instant.parse("2018-11-30T18:35:24.00Z")))
                .thenReturn(List.of(
                                new Client().setName("John")
                                        .setSurname("Black")
                                        .setWage(BigDecimal.valueOf(1.1))
                        )
                );

        assertThrows(BusinessException.class, () -> clientService.registerClient(dto));
    }

    @Test
    void shouldSuccessfullyProcess() {
        ClientRegistrationDto dto = new ClientRegistrationDto()
                .setName("John")
                .setSurname("Black")
                .setWage(BigDecimal.ONE)
                .setEventTime(Instant.parse("2018-11-30T18:35:24.100Z"));

        when(clientRepository.findAllByEventTime(Instant.parse("2018-11-30T18:35:24Z")))
                .thenReturn(List.of());

        clientService.registerClient(dto);

        verify(clientProducer).sendClientRegistrationMessage(clientRegistrationDtoArgumentCaptor.capture());
        ClientRegistrationDto dtoToBeSent = clientRegistrationDtoArgumentCaptor.getValue();

        Assertions.assertThat(dtoToBeSent.getName()).isEqualTo("John");
        Assertions.assertThat(dtoToBeSent.getSurname()).isEqualTo("Black");
        Assertions.assertThat(dtoToBeSent.getEventTime()).isEqualTo(Instant.parse("2018-11-30T18:35:24Z"));
        Assertions.assertThat(dtoToBeSent.getWage())
                .usingComparator(BigDecimal::compareTo)
                .isEqualTo(BigDecimal.valueOf(1));
    }

}
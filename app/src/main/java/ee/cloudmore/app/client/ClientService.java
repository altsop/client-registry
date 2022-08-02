package ee.cloudmore.app.client;

import ee.cloudmore.app.client.model.Client;
import ee.cloudmore.app.client.model.ClientRegistrationDto;
import ee.cloudmore.app.client.queue.ClientProducer;
import ee.cloudmore.app.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientProducer clientProducer;
    private final ClientRepository clientRepository;

    public void registerClient(ClientRegistrationDto clientRegistrationDto) {
        ClientRegistrationDto clientWithoutMillisInEventTime = withoutMillisInEventTime(clientRegistrationDto);

        if (isDuplicate(clientWithoutMillisInEventTime)) {
            throw new BusinessException(
                    format("Attempt to register client twice: %s", clientRegistrationDto)
            );
        }

        clientProducer.sendClientRegistrationMessage(clientWithoutMillisInEventTime);
    }

    private boolean isDuplicate(ClientRegistrationDto clientRegistrationDto) {
        List<Client> clients = clientRepository.findAllByEventTime(clientRegistrationDto.getEventTime());
        return clients.stream()
                .anyMatch(client -> client.getName().equals(clientRegistrationDto.getName()) &&
                        client.getSurname().equals(clientRegistrationDto.getSurname())
                );
    }

    private ClientRegistrationDto withoutMillisInEventTime(ClientRegistrationDto clientRegistrationDto) {
        Instant withoutMillis = clientRegistrationDto.getEventTime().truncatedTo(ChronoUnit.SECONDS);
        return clientRegistrationDto.setEventTime(withoutMillis);
    }

}

package ee.cloudmore.app.client;

import ee.cloudmore.app.client.model.ClientRegistrationDto;
import ee.cloudmore.app.client.queue.ClientProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientProducer clientProducer;

    public void registerClient(ClientRegistrationDto clientRegistrationDto) {
        clientProducer.sendClientRegistrationMessage(
                withoutMillisInEventTime(clientRegistrationDto)
        );
    }

    private ClientRegistrationDto withoutMillisInEventTime(ClientRegistrationDto clientRegistrationDto) {
        Instant withoutMillis = clientRegistrationDto.getEventTime().truncatedTo(ChronoUnit.SECONDS);
        return clientRegistrationDto.setEventTime(withoutMillis);
    }

}

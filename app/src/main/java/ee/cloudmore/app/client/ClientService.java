package ee.cloudmore.app.client;

import ee.cloudmore.app.client.model.ClientRegistrationDto;
import ee.cloudmore.app.client.queue.ClientProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientProducer clientProducer;

    public void registerClient(ClientRegistrationDto clientRegistrationDto) {
        clientProducer.sendClientRegistrationMessage(clientRegistrationDto);
    }

}

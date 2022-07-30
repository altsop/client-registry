package ee.cloudmore.app.client;

import ee.cloudmore.app.client.model.ClientRegistrationDto;
import ee.cloudmore.app.client.queue.ClientSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientSender clientSender;

    public void registerClient(ClientRegistrationDto clientRegistrationDto) {
        clientSender.sendClientRegistrationMessage(clientRegistrationDto);
    }

}

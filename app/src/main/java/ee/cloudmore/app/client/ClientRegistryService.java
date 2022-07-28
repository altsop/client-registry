package ee.cloudmore.app.client;

import ee.cloudmore.app.client.model.ClientRegistrationDto;
import ee.cloudmore.app.client.queue.ClientRegistrationSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientRegistryService {

    private final ClientRegistrationSender clientRegistrationSender;

    public ClientRegistrationDto test() {
        return new ClientRegistrationDto().setName("it works!");
    }

    public void registerClient(ClientRegistrationDto clientRegistrationDto) {
        clientRegistrationSender.sendClientRegistrationMessage(clientRegistrationDto);
    }

}

package ee.cloudmore.app.client;

import ee.cloudmore.app.client.model.ClientDto;
import org.springframework.stereotype.Service;

@Service
public class ClientRegistryService {

    public ClientDto test() {
        return new ClientDto().setName("it works!");
    }

    public ClientDto test(ClientDto clientDto) {
        return clientDto;
    }

}

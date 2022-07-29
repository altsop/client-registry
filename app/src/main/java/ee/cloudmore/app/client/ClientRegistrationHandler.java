package ee.cloudmore.app.client;

import ee.cloudmore.app.client.model.Client;
import ee.cloudmore.app.client.model.ClientRegistrationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ClientRegistrationHandler {

    private final ClientRepository registrationRepository;

    public void process(ClientRegistrationDto clientRegistrationDto) {
        Client client = new Client()
                .setName(clientRegistrationDto.getName())
                .setSurname(clientRegistrationDto.getSurname())
                .setWage(clientRegistrationDto.getWage())
                .setEventTime(clientRegistrationDto.getEventTime());

        registrationRepository.save(client);
    }

}

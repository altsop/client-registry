package ee.cloudmore.app.client.queue;

import ee.cloudmore.app.client.model.ClientRegistrationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ClientSender {

    private final TopicProperties properties;
    private final KafkaTemplate<String, ClientRegistrationDto> clientTemplate;

    public void sendClientRegistrationMessage(ClientRegistrationDto clientRegistrationDto) {
        clientTemplate.send(properties.getClient().getRegistration(), clientRegistrationDto);
        log.info("client registration message is sent: {}", clientRegistrationDto.toString());
    }

}

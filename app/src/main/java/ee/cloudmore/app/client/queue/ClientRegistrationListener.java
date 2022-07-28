package ee.cloudmore.app.client.queue;

import ee.cloudmore.app.client.ClientRegistrationHandler;
import ee.cloudmore.app.client.model.ClientRegistrationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ClientRegistrationListener {

    private final ClientRegistrationHandler clientRegistrationHandler;

    @KafkaListener(topics = "${topic.client.registration}")
    public void abc(@Payload ClientRegistrationDto clientRegistrationDto) {
        clientRegistrationHandler.process(clientRegistrationDto);
    }

}

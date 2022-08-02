package ee.cloudmore.app.client.queue;

import ee.cloudmore.app.client.model.ClientRegistrationDto;
import ee.cloudmore.app.client.registration.ClientRegistrationHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ClientConsumer {

    private final ClientRegistrationHandler clientRegistrationHandler;

    @KafkaListener(topics = "${topic.client.registration}")
    public void register(@Payload ClientRegistrationDto clientRegistrationDto, Acknowledgment ack) {
        log.info("Client registration process is started: {}", clientRegistrationDto);
        clientRegistrationHandler.process(clientRegistrationDto);
        log.info("Client registration process is ended: {}", clientRegistrationDto);

        ack.acknowledge();
    }

}

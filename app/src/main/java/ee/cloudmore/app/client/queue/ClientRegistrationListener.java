package ee.cloudmore.app.client.queue;

import ee.cloudmore.app.client.model.ClientRegistrationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ClientRegistrationListener {

    @KafkaListener(topics = "${topic.client.registration}")
    public void abc(@Payload ClientRegistrationDto clientRegistrationDto) {
        log.info("client registration message is received {}", clientRegistrationDto.toString());
    }

}

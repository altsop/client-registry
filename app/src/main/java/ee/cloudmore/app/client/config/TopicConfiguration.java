package ee.cloudmore.app.client.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(TopicProperties.class)
public class TopicConfiguration {

    private final TopicProperties properties;

    @Bean
    public NewTopic customerRegistrationTopic() {
        return TopicBuilder
                .name(properties.getClient().getRegistration())
                .build();
    }

}

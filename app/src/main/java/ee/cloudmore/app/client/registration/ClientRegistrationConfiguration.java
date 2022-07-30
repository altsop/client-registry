package ee.cloudmore.app.client.registration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ClientRegistrationProperties.class)
public class ClientRegistrationConfiguration {
}

package ee.cloudmore.app.client.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Validated
@ConstructorBinding
@RequiredArgsConstructor
@ConfigurationProperties("topic")
public class TopicProperties {

    @Valid
    @NotNull
    private final CustomerTopicProperties client;

    @Getter
    @Validated
    @ConstructorBinding
    @RequiredArgsConstructor
    public static class CustomerTopicProperties {

        @NotBlank
        private final String registration;

    }

}

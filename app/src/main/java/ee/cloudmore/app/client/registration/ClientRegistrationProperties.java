package ee.cloudmore.app.client.registration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Validated
@ConstructorBinding
@RequiredArgsConstructor
@ConfigurationProperties("client.registration")
public class ClientRegistrationProperties {

    @Valid
    @NotNull
    private final Wage wage;

    @Getter
    @Validated
    @ConstructorBinding
    @RequiredArgsConstructor
    public static class Wage {

        @NotNull
        @DecimalMin("0")
        @DecimalMax("100")
        @Digits(integer = 2, fraction = 0)
        private final BigDecimal tax;

    }

}

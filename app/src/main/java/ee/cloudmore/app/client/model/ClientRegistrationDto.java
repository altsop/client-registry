package ee.cloudmore.app.client.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@Validated
@Accessors(chain = true)
public class ClientRegistrationDto {

    @NotBlank
    @Size(max = 256)
    private String name;

    @NotBlank
    @Size(max = 256)
    private String surname;

    @NotNull
    @DecimalMin(value = "0.0")
    @Digits(integer = 10, fraction = 2)
    private BigDecimal wage;

    @NotNull
    private Instant eventTime;

}

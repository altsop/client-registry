package ee.cloudmore.app.client.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Validated
@Accessors(chain = true)
public class ClientRegistrationDto {

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @NotNull
    private BigDecimal wage;

    @NotNull
    private OffsetDateTime eventTime;

}

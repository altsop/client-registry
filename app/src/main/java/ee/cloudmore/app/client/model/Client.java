package ee.cloudmore.app.client.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@Accessors(chain = true)
public class Client {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID uuid;

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

package ee.cloudmore.app.client.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ClientDto {

    private String name;

}

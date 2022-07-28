package ee.cloudmore.app.client;

import ee.cloudmore.app.client.model.Client;
import ee.cloudmore.app.client.model.ClientRegistrationDto;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import static org.mapstruct.factory.Mappers.getMapper;

@Component
@RequiredArgsConstructor
public class ClientRegistrationHandler {

    private final ClientRepository registrationRepository;
    private static final ClientMapper mapper = getMapper(ClientMapper.class);

    public void process(ClientRegistrationDto clientRegistrationDto) {
        Client client = mapper.map(clientRegistrationDto);
        registrationRepository.save(client);
    }

    @Mapper
    public interface ClientMapper {

        Client map(ClientRegistrationDto source);

    }

}

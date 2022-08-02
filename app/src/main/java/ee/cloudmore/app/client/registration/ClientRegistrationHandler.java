package ee.cloudmore.app.client.registration;

import ee.cloudmore.app.client.ClientRepository;
import ee.cloudmore.app.client.model.Client;
import ee.cloudmore.app.client.model.ClientRegistrationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;


@Component
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ClientRegistrationHandler {

    private static final BigDecimal HUNDRED = BigDecimal.valueOf(100L);

    private final ClientRepository clientRepository;
    private final ClientRegistrationProperties clientRegistrationProperties;

    public Client process(ClientRegistrationDto clientRegistrationDto) {
        BigDecimal wageWithTax = calculateWageWithTax(clientRegistrationDto);

        if (isDuplicate(clientRegistrationDto, wageWithTax)) {
            log.warn("Attempt to register client twice: {}", clientRegistrationDto);
            return null;
        }

        Client client = new Client()
                .setName(clientRegistrationDto.getName())
                .setSurname(clientRegistrationDto.getSurname())
                .setWage(wageWithTax)
                .setEventTime(clientRegistrationDto.getEventTime());

        return clientRepository.save(client);
    }

    private boolean isDuplicate(ClientRegistrationDto clientRegistrationDto, BigDecimal wageWithTax) {
        List<Client> clients = clientRepository.findAllByEventTime(clientRegistrationDto.getEventTime());
        return clients.stream()
                .anyMatch(client -> client.getName().equals(clientRegistrationDto.getName()) &&
                        client.getSurname().equals(clientRegistrationDto.getSurname()) &&
                        client.getWage().compareTo(wageWithTax) == 0);
    }

    private BigDecimal calculateWageWithTax(ClientRegistrationDto clientRegistrationDto) {
        BigDecimal tax = clientRegistrationDto.getWage()
                .multiply(clientRegistrationProperties.getWage().getTax())
                .divide(HUNDRED, 2, RoundingMode.HALF_UP);

        return clientRegistrationDto.getWage().add(tax);
    }

}

package ee.cloudmore.app.client;

import ee.cloudmore.app.client.model.ClientRegistrationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/client")
public class ClientController {

    private final ClientService clientService;

    @PostMapping("register")
    public void register(@RequestBody @Valid ClientRegistrationDto clientRegistrationDto) {
        clientService.registerClient(clientRegistrationDto);
    }

}

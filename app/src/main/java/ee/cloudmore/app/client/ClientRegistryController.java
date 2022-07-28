package ee.cloudmore.app.client;

import ee.cloudmore.app.client.model.ClientRegistrationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/client/registry")
public class ClientRegistryController {

    private final ClientRegistryService clientRegistryService;

    @GetMapping("test")
    public Object test() {
        return clientRegistryService.test();
    }

    @PostMapping
    public void test(@RequestBody @Valid ClientRegistrationDto clientRegistrationDto) {
        clientRegistryService.registerClient(clientRegistrationDto);
    }

}

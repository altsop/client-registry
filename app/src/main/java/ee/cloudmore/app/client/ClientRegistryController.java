package ee.cloudmore.app.client;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/client/registry")
public class ClientRegistryController {

    private final ClientRegistryService clientRegistryService;

    @GetMapping("test")
    public Object test() {
        return clientRegistryService.test();
    }

}
